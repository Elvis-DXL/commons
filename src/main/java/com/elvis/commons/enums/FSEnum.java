package com.elvis.commons.enums;

import com.elvis.commons.utils.StrUtil;

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

    private Boolean validByFS(String fileSuffix) {
        if (StrUtil.isEmpty(fileSuffix) || StrUtil.isEmpty(fileSuffix.trim())) {
            return Boolean.FALSE;
        }
        return this.suffix.equals(fileSuffix.trim().toLowerCase());
    }

    public Boolean validByFN(String fileName) {
        if (StrUtil.isEmpty(fileName) || StrUtil.isEmpty(fileName.trim()) || SymEnum.YWD.notIncluded(fileName)) {
            return Boolean.FALSE;
        }
        return this.validByFS(fileName.substring(fileName.lastIndexOf(SymEnum.YWD.sym())));
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
