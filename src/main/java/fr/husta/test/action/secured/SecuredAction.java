package fr.husta.test.action.secured;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * HTTP Session : see https://struts.apache.org/getting-started/http-session.html
 */
public class SecuredAction extends ActionSupport implements SessionAware {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<String, Object> session;

    @Override
    public String execute() throws Exception {
        logger.debug("HTTP Session ? {}", (session != null));
        logger.debug("HTTP Session size = {}", session.size());

        return SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
