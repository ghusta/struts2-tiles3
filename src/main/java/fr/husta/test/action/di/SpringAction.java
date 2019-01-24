package fr.husta.test.action.di;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.inject.Inject;
import fr.husta.test.service.MyService;
import org.apache.struts2.StrutsConstants;
import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Action
public class SpringAction
        extends ActionSupport {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private MyService myService;

    @Inject(StrutsConstants.STRUTS_OBJECTFACTORY)
    private String strutsObjectFactory;

    public void setMyService(MyService myService) {
        this.myService = myService;
    }

    @Override
    public String execute() throws Exception {
        logger.info("CONST {} = {}", StrutsConstants.STRUTS_OBJECTFACTORY, strutsObjectFactory);
        logger.info("myService = {}", myService);

        return SUCCESS;
    }

}
