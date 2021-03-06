package fr.husta.test.tiles.definition;

import org.apache.struts2.tiles.annotation.TilesDefinition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE })
@TilesDefinition(extend = "tiles")
public @interface CustomTestTilesDefinition
{
}
