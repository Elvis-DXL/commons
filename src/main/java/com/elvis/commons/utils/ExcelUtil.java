package com.elvis.commons.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.elvis.commons.enums.FSEnum;
import com.elvis.commons.pojo.MultipleSheetExport;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Base64;
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

    private static void dealWebExportExcelResponseHeader(String fileName,
                                                         HttpServletRequest request, HttpServletResponse response) {
        fileName = DownloadUtil.encodeDownloadFileName(fileName, request);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Access-Control-Expose-Headers", "Content-disposition");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
    }

    public static void exportExcel(Workbook wb, String fileName,
                                   HttpServletRequest request, HttpServletResponse response) {
        if (null == wb) {
            throw new IllegalArgumentException("wb must not be null");
        }

        fileName = wb instanceof HSSFWorkbook ?
                (fileName + FSEnum.XLS.suffix()) : (fileName + FSEnum.XLSX.suffix());

        dealWebExportExcelResponseHeader(fileName, request, response);
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
        dealWebExportExcelResponseHeader(fileName + FSEnum.XLSX.suffix(), request, response);
        try {
            dataToExcel(dataList, clazz, sheetName, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void exportExcel(List<MultipleSheetExport> dataList, String fileName,
                                   HttpServletRequest request, HttpServletResponse response) {
        dealWebExportExcelResponseHeader(fileName + FSEnum.XLSX.suffix(), request, response);
        try {
            dataToExcel(dataList, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> excelToData(InputStream inStream, Class<T> clazz) {
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

    public static <T> List<T> excelToData(InputStream inStream, Class<T> clazz, Integer sheetIndex) {
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

    public static <T> List<T> excelToData(InputStream inStream, Class<T> clazz, String sheetName) {
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

    public static <T> void dataToExcel(List<T> dataList, Class<T> clazz, String sheetName, OutputStream outStream) {
        EasyExcelFactory.write(outStream, clazz).sheet(sheetName).doWrite(dataList);
    }

    public static void dataToExcel(List<MultipleSheetExport> dataList, OutputStream outStream) {
        if (CollUtil.isEmpty(dataList)) {
            return;
        }
        ExcelWriter excelWriter = EasyExcel.write(outStream).build();
        for (MultipleSheetExport item : dataList) {
            WriteSheet sheet = EasyExcel.writerSheet(item.getSheetName()).head(item.getClazz()).build();
            excelWriter.write(item.getData(), sheet);
        }
        excelWriter.finish();
    }

    public static void cellMerge(Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    public static void addImage(Workbook wb, Sheet sheet, int firstRow, int lastRow, int firstCol, int lastCol, String imgStr) {
        if (StringUtils.isEmpty(imgStr)) {
            return;
        }
        sheet.createRow(firstRow);
        cellMerge(sheet, firstRow, lastRow, firstCol, lastCol);
        Drawing drawingPatriarch = sheet.createDrawingPatriarch();
        ClientAnchor anchor = wb instanceof HSSFWorkbook ?
                new HSSFClientAnchor(0, 0, 0, 0, (short) firstCol, firstRow, (short) (lastCol + 1), lastRow + 1)
                : new XSSFClientAnchor(0, 0, 0, 0, (short) firstCol, firstRow, (short) (lastCol + 1), lastRow + 1);
        drawingPatriarch.createPicture(anchor, wb.addPicture(Base64.getDecoder().decode(imgStr.split("base64,")[1]),
                Workbook.PICTURE_TYPE_JPEG));
    }

}
