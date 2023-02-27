package com.elvis.commons.anno;

import java.lang.annotation.*;

/**
 * 字段脱敏
 *
 * @author : Elvis
 * @since : 2023/2/27 10:02
 */
@Inherited
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Desensitization {
    int start() default 3;

    int end() default 4;

    int mid() default 4;
}
