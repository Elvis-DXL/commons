package com.elvis.commons.enums;

/**
 * @author : Elvis
 * @since : 2022/4/18 14:02
 */
public enum FSEnum {
    //枚举定义
    XLS(".xls"),
    XLSX(".xlsx"),

    //定义结束
    ;

    private final String suffix;

    FSEnum(String suffix) {
        this.suffix = suffix;
    }

    public String suffix() {
        return suffix;
    }

    public Boolean valid(String suffixStr) {
        if (null == suffixStr || suffixStr.trim().length() == 0) {
            return Boolean.FALSE;
        }
        return this.suffix.equals(suffix.trim().toLowerCase());
    }
}
