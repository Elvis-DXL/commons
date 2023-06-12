package com.elvis.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.function.Consumer;

/**
 * 字符串工具集
 *
 * @author : Elvis
 * @since : 2020/6/24 11:04
 */
public final class StrUtil {

    private StrUtil() {
    }

    /**
     * 判断字符是否是null或者空字符串
     *
     * @param cs 字符序列
     * @return 结果
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * 判断字符是否不是null并且不是空字符串
     *
     * @param cs 字符序列
     * @return 结果
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    public static void isNotEmpty(CharSequence cs, Consumer<CharSequence> consumer) {
        if (isEmpty(cs)) {
            return;
        }
        consumer.accept(cs);
    }

    public static boolean isEmptyIgnoreTrim(String str) {
        return isEmpty(str) || isEmpty(str.trim());
    }

    public static boolean isNotEmptyIgnoreTrim(String str) {
        return !isEmptyIgnoreTrim(str);
    }

    /**
     * 分割字符串并转换成Long
     *
     * @param str            源
     * @param separatorChars 分割符
     * @return 结果集合
     */
    public static List<Long> splitToLong(String str, String separatorChars) {
        if (isEmpty(str)) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, separatorChars);
        List<Long> result = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String temp = st.nextToken();
            result.add(Long.valueOf(temp));
        }
        return result;
    }

    /**
     * 分割字符串并转换成Integer
     *
     * @param str            源
     * @param separatorChars 分割符
     * @return 结果集合
     */
    public static List<Integer> splitToInteger(String str, String separatorChars) {
        if (isEmpty(str)) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, separatorChars);
        List<Integer> result = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String temp = st.nextToken();
            result.add(Integer.valueOf(temp));
        }
        return result;
    }

    /**
     * 分割字符串
     *
     * @param str            源
     * @param separatorChars 分割符
     * @return 结果集合
     */
    public static List<String> split(String str, String separatorChars) {
        if (isEmpty(str)) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(str, separatorChars);
        List<String> result = new ArrayList<>();
        while (st.hasMoreTokens()) {
            result.add(st.nextToken());
        }
        return result;
    }

    /**
     * 数字按照要求长度前端补零
     *
     * @param num    数字
     * @param length 长度
     * @return 结果字符串
     */
    public static String intToStrByLength(int num, Integer length) {
        if (null == length) {
            return num + "";
        }
        String str = num + "";
        while (str.length() < length) {
            str = "0".concat(str);
        }
        return str;
    }
}
