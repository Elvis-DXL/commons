package com.elvis.commons.anno;

import java.lang.annotation.*;

/**
 * 字段对称加密注解
 *
 * @author : Elvis
 * @since : 2022/11/29 09:27
 */
@Inherited
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Encryption {

    String secretKey() default "123456";

}
