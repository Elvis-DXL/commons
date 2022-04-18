package com.elvis.commons.enums;

/**
 * @author : Elvis
 * @since : 2022/3/11 17:47
 */
public enum SymbolEnum {
    //枚举定义
    DH(","),
    FH(";"),
    XHX("_"),
    //定义结束    
    ;

    private final String symbol;

    SymbolEnum(String symbol) {
        this.symbol = symbol;
    }

    public String symbol() {
        return symbol;
    }
}
