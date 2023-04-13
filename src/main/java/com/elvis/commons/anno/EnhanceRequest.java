package com.elvis.commons.anno;


import com.elvis.commons.enums.AnnoEnum;

import java.lang.annotation.*;

/**
 * 增强请求
 *
 * @author : Elvis
 * @since : 2023/4/12 11:20
 */
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EnhanceRequest {
    AnnoEnum[] value() default {};
}
