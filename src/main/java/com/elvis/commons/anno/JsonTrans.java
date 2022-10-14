package com.elvis.commons.anno;


import com.elvis.commons.enums.JTTEnum;

import java.lang.annotation.*;

/**
 * 字段JSON转换注解
 *
 * @author : Elvis
 * @since : 2021/12/21 13:47
 */
@Inherited
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonTrans {

    String srcField() default "";

    JTTEnum srcType() default JTTEnum.OBJ;

    Class objClass();
}
