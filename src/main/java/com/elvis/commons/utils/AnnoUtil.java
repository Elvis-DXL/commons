package com.elvis.commons.utils;

import com.alibaba.fastjson.JSONObject;
import com.elvis.commons.anno.Desensitization;
import com.elvis.commons.anno.Encryption;
import com.elvis.commons.anno.JsonTrans;
import com.elvis.commons.anno.ListMerge;
import com.elvis.commons.enums.JTTEnum;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 注解工具集
 *
 * @author : Elvis
 * @since : 2021/11/24 16:52
 */
public final class AnnoUtil {

    private AnnoUtil() {
    }

    public static <T> void mergeListMerge(List<T> objList) {
        mergeListMerge(objList, null);
    }

    public static <T> void splitListMerge(List<T> objList) {
        splitListMerge(objList, null);
    }

    public static <T> void mergeListMerge(List<T> objList, String reducePrefix) {
        if (CollUtil.isEmpty(objList)) {
            return;
        }
        Iterator<T> iterator = objList.iterator();
        while (iterator.hasNext()) {
            mergeListMerge(iterator.next(), reducePrefix);
        }
    }

    public static <T> void splitListMerge(List<T> objList, String addPrefix) {
        if (CollUtil.isEmpty(objList)) {
            return;
        }
        Iterator<T> iterator = objList.iterator();
        while (iterator.hasNext()) {
            splitListMerge(iterator.next(), addPrefix);
        }
    }

    public static <T> void mergeListMerge(T obj) {
        mergeListMerge(obj, null);
    }

    public static <T> void splitListMerge(T obj) {
        splitListMerge(obj, null);
    }

    public static <T> void mergeListMerge(T obj, String reducePrefix) {
        if (null == obj) {
            return;
        }
        Class<?> clazz = obj.getClass();
        List<Field> fields = ClassUtil.allFields(clazz);
        if (CollUtil.isEmpty(fields)) {
            return;
        }
        Map<String, Field> fieldMap = fields.stream().collect(Collectors.toMap(Field::getName, it -> it));
        for (Field field : fields) {
            ListMerge listMerge = field.getAnnotation(ListMerge.class);
            if (null == listMerge) {
                continue;
            }
            String listFieldStr = listMerge.listField();
            if (StrUtil.isEmpty(listFieldStr)) {
                continue;
            }
            Field listField = fieldMap.get(listFieldStr);
            if (null == listField) {
                continue;
            }
            listField.setAccessible(true);
            List<String> list = null;
            try {
                list = (List<String>) listField.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            listField.setAccessible(false);
            if (CollUtil.isEmpty(list)) {
                continue;
            }
            String fieldVal = "";
            for (String item : list) {
                if (StrUtil.isEmpty(item)) {
                    continue;
                }
                if (StrUtil.isNotEmpty(reducePrefix)) {
                    item = item.substring(reducePrefix.length());
                }
                if (StrUtil.isEmpty(item)) {
                    continue;
                }
                fieldVal = fieldVal + item + listMerge.splitRegex();
            }
            if (StrUtil.isEmpty(fieldVal)) {
                continue;
            }
            fieldVal = fieldVal.substring(0, fieldVal.length() - listMerge.splitRegex().length());
            if (StrUtil.isEmpty(fieldVal)) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(obj, fieldVal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
    }

    public static <T> void splitListMerge(T obj, String addPrefix) {
        if (null == obj) {
            return;
        }
        Class<?> clazz = obj.getClass();
        List<Field> fields = ClassUtil.allFields(clazz);
        if (CollUtil.isEmpty(fields)) {
            return;
        }
        Map<String, Field> fieldMap = fields.stream().collect(Collectors.toMap(Field::getName, it -> it));
        for (Field field : fields) {
            ListMerge listMerge = field.getAnnotation(ListMerge.class);
            if (null == listMerge) {
                continue;
            }
            field.setAccessible(true);
            String fieldValue = null;
            try {
                fieldValue = (String) field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
            if (StrUtil.isEmpty(fieldValue)) {
                continue;
            }
            List<String> list = StrUtil.split(fieldValue, listMerge.splitRegex());
            List<String> result = new ArrayList<>();
            for (String item : list) {
                if (StrUtil.isEmpty(item)) {
                    continue;
                }
                if (StrUtil.isNotEmpty(addPrefix)) {
                    item = addPrefix + item;
                }
                result.add(item);
            }
            if (result.size() == 0) {
                continue;
            }
            String listFieldStr = listMerge.listField();
            if (StrUtil.isEmpty(listFieldStr)) {
                continue;
            }
            Field listField = fieldMap.get(listFieldStr);
            if (null == listField) {
                continue;
            }
            listField.setAccessible(true);
            try {
                listField.set(obj, result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            listField.setAccessible(false);
        }
    }

    public static <T> void parsingJsonTrans(List<T> objList) {
        if (CollUtil.isEmpty(objList)) {
            return;
        }
        Iterator<T> iterator = objList.iterator();
        while (iterator.hasNext()) {
            parsingJsonTrans(iterator.next());
        }
    }

    public static <T> void transJsonTrans(List<T> objList) {
        if (CollUtil.isEmpty(objList)) {
            return;
        }
        Iterator<T> iterator = objList.iterator();
        while (iterator.hasNext()) {
            transJsonTrans(iterator.next());
        }
    }

    public static <T> void parsingJsonTrans(T obj) {
        if (null == obj) {
            return;
        }
        Class<?> clazz = obj.getClass();
        List<Field> fields = ClassUtil.allFields(clazz);
        if (CollUtil.isEmpty(fields)) {
            return;
        }
        Map<String, Field> fieldMap = fields.stream().collect(Collectors.toMap(Field::getName, it -> it));
        for (Field field : fields) {
            JsonTrans jsonTrans = field.getAnnotation(JsonTrans.class);
            if (null == jsonTrans) {
                continue;
            }
            field.setAccessible(true);
            String fieldValue = null;
            try {
                fieldValue = (String) field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
            if (StrUtil.isEmpty(fieldValue)) {
                continue;
            }
            String srcFieldStr = jsonTrans.srcField();
            if (StrUtil.isEmpty(srcFieldStr)) {
                continue;
            }
            Field srtField = fieldMap.get(srcFieldStr);
            if (null == srtField) {
                continue;
            }
            JTTEnum jttEnum = jsonTrans.srcType();
            Class objClass = jsonTrans.objClass();
            srtField.setAccessible(true);
            try {
                srtField.set(obj, jttEnum.equals(JTTEnum.OBJ) ?
                        JSONObject.parseObject(fieldValue, objClass) : JSONObject.parseArray(fieldValue, objClass));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            srtField.setAccessible(false);
        }
    }

    public static <T> void transJsonTrans(T obj) {
        if (null == obj) {
            return;
        }
        Class<?> clazz = obj.getClass();
        List<Field> fields = ClassUtil.allFields(clazz);
        if (CollUtil.isEmpty(fields)) {
            return;
        }
        Map<String, Field> fieldMap = fields.stream().collect(Collectors.toMap(Field::getName, it -> it));
        for (Field field : fields) {
            JsonTrans jsonTrans = field.getAnnotation(JsonTrans.class);
            if (null == jsonTrans) {
                continue;
            }
            String srcFieldStr = jsonTrans.srcField();
            if (StrUtil.isEmpty(srcFieldStr)) {
                continue;
            }
            Field srcField = fieldMap.get(srcFieldStr);
            if (null == srcField) {
                continue;
            }
            srcField.setAccessible(true);
            Object object = null;
            try {
                object = srcField.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            srcField.setAccessible(false);
            if (null == object) {
                continue;
            }
            field.setAccessible(true);
            try {
                field.set(obj, JSONObject.toJSONString(object));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
    }

    public static <T> void encodeEncryption(List<T> objList) {
        if (CollUtil.isEmpty(objList)) {
            return;
        }
        Iterator<T> iterator = objList.iterator();
        while (iterator.hasNext()) {
            encodeEncryption(iterator.next());
        }
    }

    public static <T> void decodeEncryption(List<T> objList) {
        if (CollUtil.isEmpty(objList)) {
            return;
        }
        Iterator<T> iterator = objList.iterator();
        while (iterator.hasNext()) {
            decodeEncryption(iterator.next());
        }
    }

    public static <T> void encodeEncryption(T obj) {
        if (null == obj) {
            return;
        }
        Class<?> clazz = obj.getClass();
        List<Field> fields = ClassUtil.allFields(clazz);
        if (CollUtil.isEmpty(fields)) {
            return;
        }
        for (Field field : fields) {
            Encryption encryption = field.getAnnotation(Encryption.class);
            if (null == encryption) {
                continue;
            }
            field.setAccessible(true);
            String fieldValue = null;
            try {
                fieldValue = (String) field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (StrUtil.isEmpty(fieldValue)) {
                field.setAccessible(false);
                continue;
            }
            String secretKey = encryption.secretKey();
            if (StrUtil.isEmpty(secretKey)) {
                field.setAccessible(false);
                continue;
            }
            EncryptUtil encryptUtil = new EncryptUtil(secretKey);
            String encodeStr = encryptUtil.encode(fieldValue);
            try {
                field.set(obj, encodeStr);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
    }

    public static <T> void decodeEncryption(T obj) {
        if (null == obj) {
            return;
        }
        Class<?> clazz = obj.getClass();
        List<Field> fields = ClassUtil.allFields(clazz);
        if (CollUtil.isEmpty(fields)) {
            return;
        }
        for (Field field : fields) {
            Encryption encryption = field.getAnnotation(Encryption.class);
            if (null == encryption) {
                continue;
            }
            field.setAccessible(true);
            String fieldValue = null;
            try {
                fieldValue = (String) field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (StrUtil.isEmpty(fieldValue)) {
                field.setAccessible(false);
                continue;
            }
            String secretKey = encryption.secretKey();
            if (StrUtil.isEmpty(secretKey)) {
                field.setAccessible(false);
                continue;
            }
            EncryptUtil encryptUtil = new EncryptUtil(secretKey);
            String decodeStr = encryptUtil.decode(fieldValue);
            try {
                field.set(obj, decodeStr);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
    }

    public static <T> void desensitization(List<T> objList) {
        if (CollUtil.isEmpty(objList)) {
            return;
        }
        Iterator<T> iterator = objList.iterator();
        while (iterator.hasNext()) {
            desensitization(iterator.next());
        }
    }

    public static <T> void desensitization(T obj) {
        if (null == obj) {
            return;
        }
        Class<?> clazz = obj.getClass();
        List<Field> fields = ClassUtil.allFields(clazz);
        if (CollUtil.isEmpty(fields)) {
            return;
        }
        for (Field field : fields) {
            Desensitization desensitization = field.getAnnotation(Desensitization.class);
            if (null == desensitization) {
                continue;
            }
            field.setAccessible(true);
            String fieldValue = null;
            try {
                fieldValue = (String) field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (StrUtil.isEmpty(fieldValue)) {
                field.setAccessible(false);
                continue;
            }
            int start = desensitization.start();
            int end = desensitization.end();
            if (fieldValue.length() < (start + end)) {
                field.setAccessible(false);
                continue;
            }
            int mid = desensitization.mid();
            String midStr = "";
            while (midStr.length() < mid) {
                midStr = midStr.concat("*");
            }
            String result = fieldValue.substring(0, start) + midStr + fieldValue.substring(fieldValue.length() - end);
            try {
                field.set(obj, result);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            field.setAccessible(false);
        }
    }
}