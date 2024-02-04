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
            result.put(field.getName(), value);
        }

        return result;
    }

    public static <T, K> K copySomeFields(T src, K aim, String... fields) {
        List<Field> srcFields = ClassUtil.allFields(src.getClass());
        List<Field> aimFields = ClassUtil.allFields(aim.getClass());
        Map<String, Field> srcMap = srcFields
                .stream().collect(Collectors.toMap(Field::getName, it -> it, (k1, k2) -> k1));
        Map<String, Field> aimMap = aimFields
                .stream().collect(Collectors.toMap(Field::getName, it -> it, (k1, k2) -> k1));
        List<String> fieldList = Arrays.asList(fields);
        if (null == fieldList || fieldList.size() == 0) {
            fieldList = new ArrayList<>(srcMap.keySet());
        }
        for (String field : fieldList) {
            Field srcField = srcMap.get(field);
            Field aimField = aimMap.get(field);
            if (null == srcField || null == aimField) {
                continue;
            }
            srcField.setAccessible(true);
            aimField.setAccessible(true);
            try {
                aimField.set(aim, srcField.get(src));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } finally {
                srcField.setAccessible(false);
                aimField.setAccessible(false);
            }
        }
        return aim;
    }

    public static <T, K> void copyFields(T srcObj, K aimObj, String... ignoreFields) {
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
            aimField.setAccessible(true);
            try {
                aimField.set(aimObj, val);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            aimField.setAccessible(false);
        }
    }

    public static <T, K> T newByObj(K srcObj, Class<T> clazz) {
        T aimObj = newInstance(clazz);
        copyFields(srcObj, aimObj);
        return aimObj;
    }
}
