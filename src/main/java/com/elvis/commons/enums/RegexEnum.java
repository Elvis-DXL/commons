package com.elvis.commons.enums;

/**
 * @Author : 慕君Dxl
 * @CreateTime : 2023/11/27 14:58
 */
public enum RegexEnum {
    ID_CARD("(^[0-9]{18}$)|(^[0-9]{17}(X|x)$)", "身份证号码"),
    ID_CARD_LAST_SIX("(^[0-9]{6}$)|(^[0-9]{5}(X|x)$)", "身份证号码后6位"),
    //定义结束
    ;

    private final String regexStr;
    private final String desc;

    RegexEnum(String regexStr, String desc) {
        this.regexStr = regexStr;
        this.desc = desc;
    }

    public boolean verify(String aimStr) {
        return null != aimStr && aimStr.length() != 0 && aimStr.matches(this.regexStr);
    }
}
