package de.wroracer.chaoschallange.chaos.actions.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionInfo {
    String name();
    String description() default "";
    long delay() default 0;
    long period() default 0;
    boolean isFullTime() default false;
}
