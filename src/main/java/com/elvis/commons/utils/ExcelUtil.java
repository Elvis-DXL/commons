package com.elvis.commons.utils;

import com.elvis.commons.enums.FSEnum;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * EXCLE工具集
 *
 * @author : Elvis
 * @since : 2022/3/17 10:38
 */
public final class ExcelUtil {

    private ExcelUtil() {
    }

    public static void exportExcel(Workbook wb, String fileName,
                                   HttpServletRequest request, HttpServletResponse response) {
        fileName = fileName + FSEnum.XLS.suffix();
        fileName = DownloadUtil.encodeDownloadFileName(fileName, request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        OutputStream out = null;
        try {
            out = response.getOutputStream();
            wb.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeStream(out, wb);
        }
    }

}
