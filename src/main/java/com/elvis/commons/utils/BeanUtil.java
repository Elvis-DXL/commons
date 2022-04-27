package com.elvis.commons.utils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Bean工具集
 *
 * @author : Elvis
 * @since : 2021/8/16 15:38
 */
public final class BeanUtil {

    private BeanUtil() {
    }

    /**
     * 判断对象的所有自有属性是不是都是null
     *
     * @param obj 目标对象
     * @return 结果
     */
    public static Boolean allFieldNull(Object obj) {
        try {
            Class<?> clazz = obj.getClass();
            List<Field> fields = ClassUtil.allFields(clazz);
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                field.setAccessible(false);
                if (null != value) {
                    return false;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static <T> List<T> mapToBean(List<Map<String, Object>> mapList, Class<T> clazz) {
        if (CollUtil.isEmpty(mapList)) {
            return null;
        }
        List<T> result = new ArrayList<>();
        Iterator<Map<String, Object>> iterator = mapList.iterator();
        while (iterator.hasNext()) {
            T data = mapToBean(iterator.next(), clazz);
            if (null == data) {
                continue;
            }
            result.add(data);
        }
        return result;
    }

    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        Set keySet = map.keySet();
        if (CollUtil.isEmpty(keySet)) {
            return null;
        }
        T data = null;
        try {
            data = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        List<Field> fields = ClassUtil.allFields(clazz);
        for (Field field : fields) {
            String fieldName = field.getName();
            Object value = map.get(fieldName);
            if (null == value) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(data, value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
        return data;
    }

    public static void copyField(Object srcObj, Object aimObj, String... ignoreField) {
        List<String> ignore = new ArrayList<>();
        if (null != ignoreField && ignoreField.length > 0) {
            ignore.addAll(Arrays.asList(ignoreField));
        }
        List<Field> srcFields = ClassUtil.allFields(srcObj.getClass());
        List<Field> aimFields = ClassUtil.allFields(aimObj.getClass());
        Map<String, Field> srcMap = srcFields.stream().collect(Collectors.toMap(Field::getName, it -> it));
        for (Field aimField : aimFields) {
            if (ignore.contains(aimField.getName())) {
                continue;
            }
            Field srcField = srcMap.get(aimField.getName());
            if (null == srcField) {
                continue;
            }
            Object val = null;
            srcField.setAccessible(true);
            try {
                val = srcField.get(srcObj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            srcField.setAccessible(false);
            if (null == val) {
                continue;
            }
            aimField.setAccessible(true);
            try {
                aimField.set(aimObj, val);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            aimField.setAccessible(false);
        }
    }
}
