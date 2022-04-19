package com.elvis.commons.anno;

import java.lang.annotation.*;

/**
 * 字段字符串集合拼接拆分注解
 *
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
