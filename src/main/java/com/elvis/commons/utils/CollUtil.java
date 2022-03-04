package com.elvis.commons.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Elvis
 * @since : 2021/12/7 14:42
 */
public final class CollUtil {

    private CollUtil() {
    }

    public static <T> List<T> mergeInto(List<T> srcList, List<T> aimList) {
        if (null == srcList) {
            srcList = new ArrayList<>();
        }
        if (null == aimList || aimList.size() == 0) {
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
