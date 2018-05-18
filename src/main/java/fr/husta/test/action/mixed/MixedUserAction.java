package fr.husta.test.action.mixed;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import static fr.husta.test.security.WebSecurityConstants.Roles.USER;

@PreAuthorize("hasRole('" + USER + "')")
public class MixedUserAction extends ActionSupport {

    private String name;

    public String getName() {
        return name;
    }

    @Override
    @SkipValidation
    public String execute() throws Exception {
        // test property display with Struts' ValueStack
        name = String.format("Hello World in %s", this.getClass().getName());

        return SUCCESS;
    }

}
