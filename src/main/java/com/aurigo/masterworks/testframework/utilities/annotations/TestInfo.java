package com.aurigo.masterworks.testframework.utilities.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestInfo {
    String[] testIds() default "";

    String[] tags() default "";

    String  downloadPath() default  "";
}

