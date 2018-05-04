package fr.husta.test.util;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.core.annotation.AnnotationUtils;

import javax.annotation.security.RolesAllowed;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.List;

public class AnnotationSecureUtils {

    /**
     * Trouver l'annotation {@link RolesAllowed @RolesAllowed}.
     *
     * @param annotatedElement
     * @return
     */
    public static RolesAllowed findAnnotationRolesAllowed(AnnotatedElement annotatedElement) {
        return AnnotationUtils.findAnnotation(annotatedElement, RolesAllowed.class);
    }

    public static List<Method> getMethodsListWithAnnotationRolesAllowed(final Class<?> cls) {
        return MethodUtils.getMethodsListWithAnnotation(cls, RolesAllowed.class);
    }


}
