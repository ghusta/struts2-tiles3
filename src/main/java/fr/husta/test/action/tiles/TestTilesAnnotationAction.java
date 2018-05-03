package fr.husta.test.action.tiles;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.tiles.annotation.TilesDefinition;
import org.apache.struts2.tiles.annotation.TilesPutAttribute;

import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 * URL = /tiles/test-tiles-annotation
 */
@Result(name = SUCCESS, type = "tiles")
@TilesDefinition(extend = "layout",
        putAttributes = {
                @TilesPutAttribute( name = "title", value = "Test with annotations / without XML"),
                @TilesPutAttribute( name = "header", value = "Header test..."),
                @TilesPutAttribute( name = "body", value = "/WEB-INF/content/test/test.jsp")
        })
public class TestTilesAnnotationAction
        extends ActionSupport {

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

}
