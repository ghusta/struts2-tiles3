package fr.husta.test.action.test;

import com.opensymphony.xwork2.ActionSupport;
import fr.husta.test.tiles.definition.CustomTestTilesDefinition;
import org.apache.struts2.convention.annotation.Result;

/**
 * URL = /test/test-tiles
 */
@Result(name = "success", type = "tiles")
//@TilesDefinition(extend = "")
@CustomTestTilesDefinition
public class TestTilesAction
        extends ActionSupport
{

    @Override
    public String execute() throws Exception
    {

        return SUCCESS;
    }

}
