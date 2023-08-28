package com.elvis.commons.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
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

    public static void isNotEmpty(Collection<?> coll, Consumer<Collection<?>> consumer) {
        if (isEmpty(coll)) {
            return;
        }
        consumer.accept(coll);
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
        if (null == strArr || strArr.length == 0) {
            return null;
        }
        return joinStr(Arrays.asList(strArr), separatorChars);
    }

    /**
     * 集合元素类型改变
     *
     * @param srcList 源集合
     * @param clazz   目标类型
     * @param <T>     泛型
     * @param <K>     泛型
     * @return 结果集合
     * @author 慕君Dxl
     * @createTime 2023/8/28 16:03
     */
    public static <T, K> List<T> classChange(List<K> srcList, Class<T> clazz) {
        if (isEmpty(srcList)) {
            return new ArrayList<>();
        }
        List<T> result = new ArrayList<>();
        for (K item : srcList) {
            T aim = BeanUtil.newInstance(clazz);
            BeanUtil.copyFields(item, aim);
            result.add(aim);
        }
        return result;
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

    /**
     * 集合去重
     *
     * @param srcList 源集合
     * @param <T>     泛型
     * @return 去重后集合
     * @author 慕君Dxl
     * @createTime 2023/8/28 15:58
     */
    public static <T> List<T> distinct(List<T> srcList) {
        if (null == srcList) {
            return new ArrayList<>();
        }
        return srcList.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 随机从集合中取出一个元素
     *
     * @param srcList 源数据集合
     * @param <T>     泛型
     * @return 随机取出的元素
     * @author 慕君Dxl
     * @createTime 2023/8/24 18:10
     */
    public static <T> T randomGetOne(List<T> srcList) {
        return srcList.get((int) (Math.random() * srcList.size()));
    }
}
