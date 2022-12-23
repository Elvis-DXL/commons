package com.elvis.commons.utils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.elvis.commons.enums.FSEnum;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

    public static <T> void exportExcel(List<T> dataList, Class<T> clazz,
                                       String fileName, String sheetName,
                                       HttpServletRequest request, HttpServletResponse response) {
        fileName = fileName + FSEnum.XLSX.suffix();
        fileName = DownloadUtil.encodeDownloadFileName(fileName, request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            listToExcel(dataList, clazz, sheetName, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> excelToList(InputStream inStream, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        EasyExcelFactory.read(inStream, clazz, new AnalysisEventListener<T>() {
            @Override
            public void invoke(T rowDTO, AnalysisContext analysisContext) {
                result.add(rowDTO);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        }).sheet().doRead();
        return result;
    }

    public static <T> List<T> excelToList(InputStream inStream, Class<T> clazz, Integer sheetIndex) {
        List<T> result = new ArrayList<>();
        EasyExcelFactory.read(inStream, clazz, new AnalysisEventListener<T>() {
            @Override
            public void invoke(T rowDTO, AnalysisContext analysisContext) {
                result.add(rowDTO);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        }).sheet(sheetIndex).doRead();
        return result;
    }

    public static <T> List<T> excelToList(InputStream inStream, Class<T> clazz, String sheetName) {
        List<T> result = new ArrayList<>();
        EasyExcelFactory.read(inStream, clazz, new AnalysisEventListener<T>() {
            @Override
            public void invoke(T rowDTO, AnalysisContext analysisContext) {
                result.add(rowDTO);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
            }
        }).sheet(sheetName).doRead();
        return result;
    }

    public static <T> void listToExcel(List<T> dataList, Class<T> clazz, String sheetName, OutputStream outStream) {
        EasyExcelFactory.write(outStream, clazz).sheet(sheetName).doWrite(dataList);
    }

}
