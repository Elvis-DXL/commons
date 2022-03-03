package com.elvis.commons.old.anno;

import com.elvis.commons.old.enums.JTTypeEnum;

import java.lang.annotation.*;

/**
 * @author : Elvis
 * @since : 2021/12/21 13:47
 */
@Documented
@Target({ElementType.FIELD})
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonTransform {

    String srcField() default "";

    JTTypeEnum srcType() default JTTypeEnum.OBJ;

    Class objClass();
}