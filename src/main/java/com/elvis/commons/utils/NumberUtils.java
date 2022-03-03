package com.elvis.commons.utils;

import java.text.DecimalFormat;

/**
 * @author : Elvis
 * @since : 2021/3/26 10:20
 */
public final class NumberUtils {

    private NumberUtils() {
    }

    /**
     * 求源数据中最大值
     *
     * @param numbers 源数据
     * @return 最大值
     */
    public static Long max(Long... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Long max = numbers[0];
        for (Long number : numbers) {
            max = max > number ? max : number;
        }
        return max;
    }

    /**
     * 求源数据中最大值
     *
     * @param numbers 源数据
     * @return 最大值
     */
    public static Double max(Double... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Double max = numbers[0];
        for (Double number : numbers) {
            max = max > number ? max : number;
        }
        return max;
    }

    /**
     * 求源数据中最大值
     *
     * @param numbers 源数据
     * @return 最大值
     */
    public static Integer max(Integer... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Integer max = numbers[0];
        for (Integer number : numbers) {
            max = max > number ? max : number;
        }
        return max;
    }

    /**
     * 求源数据中最小值
     *
     * @param numbers 源数据
     * @return 最小值
     */
    public static Long min(Long... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Long min = numbers[0];
        for (Long number : numbers) {
            min = min < number ? min : number;
        }
        return min;
    }

    /**
     * 求源数据中最小值
     *
     * @param numbers 源数据
     * @return 最小值
     */
    public static Double min(Double... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Double min = numbers[0];
        for (Double number : numbers) {
            min = min < number ? min : number;
        }
        return min;
    }

    /**
     * 求源数据中最小值
     *
     * @param numbers 源数据
     * @return 最小值
     */
    public static Integer min(Integer... numbers) {
        if (numbers.length == 0) {
            return null;
        }
        Integer min = numbers[0];
        for (Integer number : numbers) {
            min = min < number ? min : number;
        }
        return min;
    }

    /**
     * 四舍五入后保留小数
     *
     * @param value 值
     * @param num   保留小数位数
     * @return 结果
     */
    public static Double doubleFmt(Double value, Integer num) {
        if (value == null) {
            return 0D;
        }
        if (num == null || num < 1) {
            return value;
        }
        StringBuilder builder = new StringBuilder("#.");
        for (int i = 0; i < num; i++) {
            builder.append("0");
        }
        DecimalFormat df = new DecimalFormat(builder.toString());
        return Double.parseDouble(df.format(value));
    }

}
