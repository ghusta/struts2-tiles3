package fr.husta.test.action.security;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.inject.Inject;
import fr.husta.test.util.AnnotationSecureUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.struts2.StrutsConstants;
import org.apache.struts2.config_browser.ConfigurationHelper;
import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.RolesAllowed;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Scans which actions are secured by annotations.
 */
@Action
public class SecurityScanAction extends ActionSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ConfigurationHelper configHelper;

    private Configuration configuration;

    private Set<String> actionNames;
    private Set<String> actionNamesNsTiles;
    private Set<String> actionNamesNsFoo;

    private Set<String> namespaces;
    private String extension;

    @Inject
    public void setConfigurationHelper(ConfigurationHelper cfg) {
        this.configHelper = cfg;
    }

    @Inject
    public void setConfiguration(Configuration config) {
        this.configuration = config;
    }

    public Set<String> getActionNames() {
        return actionNames;
    }

    public Set<String> getActionNamesNsTiles() {
        return actionNamesNsTiles;
    }

    public Set<String> getActionNamesNsFoo() {
        return actionNamesNsFoo;
    }

    public Set<String> getNamespaces() {
        return namespaces;
    }

    @Inject(StrutsConstants.STRUTS_ACTION_EXTENSION)
    public void setExtension(String ext) {
        this.extension = ext;
    }

    @Override
    public String execute() throws Exception {
        namespaces = configHelper.getNamespaces();
        if (namespaces.isEmpty()) {
            addActionError("There are no namespaces in this configuration");
            return ERROR;
        }

        actionNames = new TreeSet<>(configHelper.getActionNames(""));
        actionNamesNsTiles = new TreeSet<>(configHelper.getActionNames("/tiles"));
        actionNamesNsFoo = new TreeSet<>(configHelper.getActionNames("/foo"));

        // org.apache.struts2.config_browser.ConfigurationHelper.getActionConfig
        ActionConfig actionConfigTest = configHelper.getActionConfig("/tiles", "test-tiles");
        logger.debug("actionConfigTest : {}", actionConfigTest);
        logger.debug("actionConfigTest.packageName : {}", actionConfigTest.getPackageName());
        logger.debug("actionConfigTest.className : {}", actionConfigTest.getClassName());
        logger.debug("actionConfigTest.methodName: {}", actionConfigTest.getMethodName());

        logger.debug("@RolesAllowed sur class : {}", isClassSecured(actionConfigTest.getClassName()));

        logger.debug("@RolesAllowed sur method : {}", isMethSecured(actionConfigTest.getClassName(), actionConfigTest.getMethodName()));
        logger.debug("Roles found (on {}/{}) : {}", actionConfigTest.getClassName(), actionConfigTest.getMethodName(),
                extractRoles(actionConfigTest.getClassName(), actionConfigTest.getMethodName()));

        scanAllActions();

        return SUCCESS;
    }

    private boolean isClassSecured(String className) throws ClassNotFoundException {
        RolesAllowed annotRolesAllowedTest = AnnotationSecureUtils.findAnnotationRolesAllowed(Class.forName(className));
        return annotRolesAllowedTest != null;
    }

    private boolean isMethSecured(String className, String methodName) throws ClassNotFoundException {
        if (className == null || methodName == null) {
            return false;
        }
        RolesAllowed annotationRolesAllowed;
        try {
            Method accessibleMethod = MethodUtils.getAccessibleMethod(Class.forName(className), methodName);
            annotationRolesAllowed = AnnotationSecureUtils.findAnnotationRolesAllowed(accessibleMethod);
        } catch (Exception e) {
            logger.error("Error on class '{}' / method '{}'", className, methodName);
            throw e;
        }
        return annotationRolesAllowed != null;
    }

    private List<String> extractRoles(String className) throws ClassNotFoundException {
        return AnnotationSecureUtils.extractRolesAllowed(Class.forName(className));
    }

    private List<String> extractRoles(String className, String methodName) throws ClassNotFoundException {
        Method accessibleMethod = MethodUtils.getAccessibleMethod(Class.forName(className), methodName);
        return AnnotationSecureUtils.extractRolesAllowed(accessibleMethod);
    }

    /**
     * @return
     * @see ConfigurationHelper#getActionConfig(String, String)
     */
    private Map<String, Map<String, ActionConfig>> getActionConfigs() {
        return configuration.getRuntimeConfiguration().getActionConfigs();
    }

    private void scanAllActions() throws ClassNotFoundException {
        logger.debug("scanAllActions()");
        Map<String, Map<String, ActionConfig>> actionConfigs = getActionConfigs();

        for (String ns : actionConfigs.keySet()) {
            logger.info(" * Namespace : {}", ns);
            Map<String, ActionConfig> actionConfigMap = actionConfigs.get(ns);
            for (String actionName : actionConfigMap.keySet()) {
                logger.info("\t * Action : {}", actionName);
                ActionConfig actionConfig = actionConfigMap.get(actionName);
                logger.info("\t >> className : {}", actionConfig.getClassName());
                if (isClassSecured(actionConfig.getClassName())) {
                    logger.info("\t >> @RolesAllowed found on this class <<");
                    List<String> roles = extractRoles(actionConfig.getClassName());
                    logger.info("\t\t >> Roles found on this class : {} <<", roles);
                }
                logger.info("\t >> methodName : {}", actionConfig.getMethodName());
                if (isMethSecured(actionConfig.getClassName(), actionConfig.getMethodName())) {
                    logger.info("\t\t >> @RolesAllowed found on this method <<");
                    List<String> roles = extractRoles(actionConfig.getClassName(), actionConfig.getMethodName());
                    logger.info("\t\t >> Roles found on this method : {} <<", roles);
                }
            }
        }
    }

}
