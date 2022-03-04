package com.elvis.commons.utils;

import com.alibaba.fastjson.JSONObject;
import com.elvis.commons.anno.JsonTrans;
import com.elvis.commons.anno.ListMerge;
import com.elvis.commons.enums.JTTypeEnum;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
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
        if (null == objList || objList.size() == 0) {
            return;
        }
        Iterator<T> iterator = objList.iterator();
        while (iterator.hasNext()) {
            mergeListMerge(iterator.next(), reducePrefix);
        }
    }

    public static <T> void splitListMerge(List<T> objList, String addPrefix) {
        if (null == objList || objList.size() == 0) {
            return;
        }
        Iterator<T> iterator = objList.iterator();
        while (iterator.hasNext()) {
            splitListMerge(iterator.next(), addPrefix);
        }
    }

    public static <T> void mergeListMerge(T obj, String reducePrefix) {
        if (null == obj) {
            return;
        }
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ListMerge listMerge = field.getDeclaredAnnotation(ListMerge.class);
            if (null == listMerge) {
                continue;
            }
            String listFieldStr = listMerge.listField();
            if (StrUtil.isEmpty(listFieldStr)) {
                continue;
            }
            Field listField = null;
            try {
                listField = clazz.getDeclaredField(listFieldStr);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
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
            if (null == list || list.size() == 0) {
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
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            ListMerge listMerge = field.getDeclaredAnnotation(ListMerge.class);
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
            Field listField = null;
            try {
                listField = clazz.getDeclaredField(listFieldStr);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
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
        if (null == objList || objList.size() == 0) {
            return;
        }
        Iterator<T> iterator = objList.iterator();
        while (iterator.hasNext()) {
            parsingJsonTrans(iterator.next());
        }
    }

    public static <T> void transJsonTrans(List<T> objList) {
        if (null == objList || objList.size() == 0) {
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
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            JsonTrans jsonTrans = field.getDeclaredAnnotation(JsonTrans.class);
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
            Field srtField = null;
            try {
                srtField = clazz.getDeclaredField(srcFieldStr);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            if (null == srtField) {
                continue;
            }
            JTTypeEnum jtTypeEnum = jsonTrans.srcType();
            Class objClass = jsonTrans.objClass();
            srtField.setAccessible(true);
            try {
                srtField.set(obj, jtTypeEnum.equals(JTTypeEnum.OBJ) ?
                        JSONObject.parseObject(fieldValue, objClass)
                        : JSONObject.parseArray(fieldValue, objClass));
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
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            JsonTrans jsonTrans = field.getDeclaredAnnotation(JsonTrans.class);
            if (null == jsonTrans) {
                continue;
            }
            String srcFieldStr = jsonTrans.srcField();
            if (StrUtil.isEmpty(srcFieldStr)) {
                continue;
            }
            Field srcField = null;
            try {
                srcField = clazz.getDeclaredField(srcFieldStr);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
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
}