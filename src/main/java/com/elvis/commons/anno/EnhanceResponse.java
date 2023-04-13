package com.elvis.commons.anno;


import com.elvis.commons.enums.AnnoEnum;

import java.lang.annotation.*;

/**
 * 增强响应
 *
 * @author : Elvis
 * @since : 2023/4/12 11:52
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnhanceResponse {
    AnnoEnum[] value() default {};
}
