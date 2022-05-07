package com.elvis.commons.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 集合工具集
 *
 * @author : Elvis
 * @since : 2021/12/7 14:42
 */
public final class CollUtil {

    private CollUtil() {
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }


    /**
     * 使用指定的字符连接字符串集合
     *
     * @param strCol         字符源集合
     * @param separatorChars 分割符
     * @return 字符串结果
     */
    public static String joinStr(Collection<String> strCol, String separatorChars) {
        if (isEmpty(strCol)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strCol) {
            sb.append(str);
            sb.append(separatorChars);
        }
        String result = sb.toString();
        return result.substring(0, result.length() - separatorChars.length());
    }

    /**
     * 使用指定的字符连接字符串数组
     *
     * @param strArr         字符源数组
     * @param separatorChars 分割符
     * @return 字符串结果
     */
    public static String joinStr(String[] strArr, String separatorChars) {
        if (null == strArr || strArr.length <= 0) {
            return null;
        }
        return joinStr(Arrays.asList(strArr), separatorChars);
    }

    public static <T> List<T> mergeInto(List<T> srcList, List<T> aimList) {
        if (null == srcList) {
            srcList = new ArrayList<>();
        }
        if (isEmpty(aimList)) {
            return srcList;
        }
        for (T item : aimList) {
            if (srcList.contains(item)) {
                continue;
            }
            srcList.add(item);
        }
        return srcList;
    }

    public static <T> List<T> distinct(List<T> srcList) {
        if (null == srcList) {
            return new ArrayList<>();
        }
        return srcList.stream().distinct().collect(Collectors.toList());
    }
}
