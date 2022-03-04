package com.elvis.commons.anno;


import com.elvis.commons.enums.JTTypeEnum;

import java.lang.annotation.*;

/**
 * @author : Elvis
 * @since : 2021/12/21 13:47
 */
@Documented
@Target({ElementType.FIELD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonTrans {

    String srcField() default "";

    JTTypeEnum srcType() default JTTypeEnum.OBJ;

    Class objClass();
}