package fr.husta.test.action.security;

import com.opensymphony.xwork2.ActionSupport;
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
import java.util.Set;
import java.util.TreeSet;

/**
 * Scans which actions are secured by annotations.
 */
@Action
public class SecurityScanAction extends ActionSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ConfigurationHelper configHelper;

    private Set<String> actionNames;
    private Set<String> actionNamesNsTiles;
    private Set<String> actionNamesNsFoo;

    private Set<String> namespaces;
    private String extension;

    @Inject
    public void setConfigurationHelper(ConfigurationHelper cfg) {
        this.configHelper = cfg;
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

        RolesAllowed annotRolesAllowedTest1 = AnnotationSecureUtils.findAnnotationRolesAllowed(Class.forName(actionConfigTest.getClassName()));
        logger.debug("@RolesAllowed sur class : {}", annotRolesAllowedTest1 != null);

        logger.debug("@RolesAllowed sur method : {}", isMethSecured(actionConfigTest, actionConfigTest.getMethodName()));

        return SUCCESS;
    }

    private boolean isMethSecured(ActionConfig actionConfigTest, String methodName) throws ClassNotFoundException {
        Method accessibleMethod = MethodUtils.getAccessibleMethod(Class.forName(actionConfigTest.getClassName()), methodName);
        RolesAllowed annotationRolesAllowed = AnnotationSecureUtils.findAnnotationRolesAllowed(accessibleMethod);
        return annotationRolesAllowed != null;
    }


}
