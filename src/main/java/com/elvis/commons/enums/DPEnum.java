package com.elvis.commons.enums;

/**
 * @author : Elvis
 * @since : 2022/3/3 17:33
 */
public enum DPEnum {
    //枚举定义
    yyyy_MM_dd(""),
    HH_mm_ss(""),
    yyyy_MM_dd_HH_mm_ss(""),;

    private final String pattern;

    DPEnum(String pattern) {
        this.pattern = pattern;
    }

    public String pattern() {
        return pattern;
    }
}