package com.elvis.commons.enums;

/**
 * 文件后缀名定义枚举
 *
 * @author : Elvis
 * @since : 2022/4/18 14:02
 */
public enum FSEnum {
    //枚举定义
    XLS(".xls"),
    XLSX(".xlsx"),
    DOC(".doc"),
    DOCX(".docx"),
    XML(".xml"),
    JPG(".jpg"),
    JPEG(".jpeg"),
    TXT(".txt"),

    //定义结束
    ;

    private final String suffix;

    FSEnum(String suffix) {
        this.suffix = suffix;
    }

    public String suffix() {
        return suffix;
    }

    public Boolean validByFS(String fileSuffix) {
        if (null == fileSuffix || fileSuffix.trim().length() == 0) {
            return Boolean.FALSE;
        }
        return this.suffix.equals(fileSuffix.trim().toLowerCase());
    }

    public Boolean validByFN(String fileName) {
        if (null == fileName || fileName.trim().length() == 0) {
            return Boolean.FALSE;
        }
        if (!fileName.contains(SymbolEnum.YWD.symbol())) {
            return Boolean.FALSE;
        }
        return this.validByFS(fileName.substring(fileName.lastIndexOf(SymbolEnum.YWD.symbol())));
    }

    public static Boolean isAnyOne(String fileName, FSEnum... fsEnums) {
        if (null == fsEnums || fsEnums.length == 0) {
            return Boolean.FALSE;
        }
        for (FSEnum fsEnum : fsEnums) {
            if (fsEnum.validByFN(fileName)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public static Boolean isExecl(String fileName) {
        return isAnyOne(fileName, XLS, XLSX);
    }

}
