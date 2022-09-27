package com.elvis.commons.enums;


import com.elvis.commons.utils.StrUtil;

/**
 * 特殊符号定义枚举
 *
 * @author : Elvis
 * @since : 2022/3/11 17:47
 */
public enum SymEnum {
    //枚举定义
    DH(","),
    FH(";"),
    XHX("_"),
    ZHX("-"),
    YWD("."),
    BFH("%"),
    MYF("$"),
    RMB("￥"),
    ADF("@"),

    //定义结束
    ;

    private final String sym;

    SymEnum(String sym) {
        this.sym = sym;
    }

    public String sym() {
        return this.sym;
    }

    public Boolean included(String aimStr) {
        return StrUtil.isNotEmptyIgnoreTrim(aimStr) && aimStr.trim().contains(this.sym);
    }

    public Boolean notIncluded(String aimStr) {
        return !this.included(aimStr);
    }
}
