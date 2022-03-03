package com.elvis.commons.zzold.anno;

import java.lang.annotation.*;

/**
 * @author : Elvis
 * @since : 2021/11/24 16:38
 */
@Documented
@Target({ElementType.FIELD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ListMerge {

    String listField() default "";

    String splitRegex() default ",";
}
