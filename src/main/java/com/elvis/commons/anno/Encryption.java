package com.elvis.commons.anno;

import java.lang.annotation.*;

/**
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
