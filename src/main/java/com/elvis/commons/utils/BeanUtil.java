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

    public static <T> T newInstance(Class<T> clazz) {
        if (null == clazz) {
            throw new IllegalArgumentException("Class must not be null");
        }
        if (clazz.isInterface()) {
            throw new IllegalArgumentException("Specified class is an interface");
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 判断对象的所有属性是不是都是null
     *
     * @param obj 目标对象
     * @return 结果
     */
    public static <T> Boolean allFieldNull(T obj) {
        try {
            Class<?> clazz = obj.getClass();
            List<Field> fields = ClassUtil.allFields(clazz);
            if (CollUtil.isEmpty(fields)) {
                return Boolean.TRUE;
            }
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(obj);
                field.setAccessible(false);
                if (null != value) {
                    return Boolean.FALSE;
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return Boolean.TRUE;
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
        T data = newInstance(clazz);
        List<Field> fields = ClassUtil.allFields(clazz);
        if (CollUtil.isEmpty(fields)) {
            return data;
        }
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

    public static <T> Map<String, Object> beanToMap(T aimObj) {
        List<Field> fields = ClassUtil.allFields(aimObj.getClass());
        if (CollUtil.isEmpty(fields)) {
            return new HashMap<>();
        }
        Map<String, Object> result = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(aimObj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
            if (null == value) {
                continue;
            }
            result.put(field.getName(), value);
        }

        return result;
    }

    public static void copyFields(Object srcObj, Object aimObj, String... ignoreFields) {
        List<String> ignore = new ArrayList<>();
        if (null != ignoreFields && ignoreFields.length > 0) {
            ignore.addAll(Arrays.asList(ignoreFields));
        }
        List<Field> srcFields = ClassUtil.allFields(srcObj.getClass());
        List<Field> aimFields = ClassUtil.allFields(aimObj.getClass());
        if (CollUtil.isEmpty(srcFields) || CollUtil.isEmpty(aimFields)) {
            return;
        }
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
