package com.elvis.commons.enums;

/**
 * @author : Elvis
 * @since : 2022/4/18 14:02
 */
public enum FSEnum {
    //枚举定义
    XLS(".xls"),;

    private final String suffix;

    FSEnum(String suffix) {
        this.suffix = suffix;
    }

    public String suffix() {
        return suffix;
    }
}
