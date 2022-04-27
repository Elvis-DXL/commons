package com.elvis.commons.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Elvis
 * @since : 2022/4/27 14:13
 */
public final class ClassUtil {

    private ClassUtil() {
    }

    public static List<Field> allField(Class clazz) {
        List<Field> result = new ArrayList<>();
        do {
            Field[] fields = clazz.getDeclaredFields();
            if (null != fields && fields.length > 0) {
                result.addAll(Arrays.asList(fields));
            }
            clazz = clazz.getSuperclass();
        } while (null != clazz && !clazz.equals(Object.class));
        return result;
    }
}
