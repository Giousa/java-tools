package com.giousa.tools.excel;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class POIUtil {
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    public static List<String> getSheets(String fileName) {
        try {
            // 判断文件是否是excel文件
            if (StringUtils.isBlank(fileName) || (!fileName.endsWith(xls) && !fileName.endsWith(xlsx))) {
                return null;
            }

            // 获得Workbook工作薄对象
            Workbook workbook = getWorkBook(fileName);
            if (Objects.isNull(workbook)) {
                return null;
            }

            List<String> sheets = Lists.newArrayList();
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                sheets.add(sheet.getSheetName());
            }
            return sheets;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ImmutablePair<Map<String, List<String>>, Map<String, List<String[]>>> readExcel(String fileName) {
        // 判断文件是否是excel文件
        if (StringUtils.isBlank(fileName) || (!fileName.endsWith(xls) && !fileName.endsWith(xlsx))) {
            return null;
        }

        // 获得Workbook工作薄对象
        Workbook workbook = getWorkBook(fileName);
        if (Objects.isNull(workbook)) {
            return null;
        }

        // 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        Map<String, List<String[]>> cellMap = new HashMap<>();
        Map<String, List<String>> headMap = new HashMap<>();
        for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) { // 获得当前sheet工作表
            Sheet sheet = workbook.getSheetAt(sheetNum);
            if (sheet == null) {
                continue;
            }
            System.out.println("sheet信息： " + sheet.getSheetName());

            List<String> heads = Lists.newArrayList();
            List<String[]> cellList = Lists.newArrayList();


            // 获得当前sheet的开始行
            int firstRowNum = sheet.getFirstRowNum();
            // 获得当前sheet的结束行
            int lastRowNum = sheet.getLastRowNum();
            //获取头部信息
            Row headRow = sheet.getRow(firstRowNum);

            if (Objects.nonNull(headRow)) {
                // 循环当前行
                for (int cellNum = headRow.getFirstCellNum(); cellNum < headRow.getPhysicalNumberOfCells(); cellNum++) {
                    String cellValue = getCellValue(headRow.getCell(cellNum));
                    if (StringUtils.isBlank(cellValue)) {
                        break;
                    }
                    heads.add(cellValue);
                }
            }

            headMap.put(sheet.getSheetName(), heads);
            if (CollectionUtils.isEmpty(heads)) {
                System.out.println("头部消息不存在，忽略消息");
                return null;
            }

            // 循环所有行  如果需要排除第一行，下面的循环，firstRowNum+1即可
            for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                // 获得当前行
                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                // 获得当前行的开始列
                int firstCellNum = row.getFirstCellNum();

                // 获得当前行的列数
                int lastCellNum = row.getPhysicalNumberOfCells();

                String[] cells = new String[heads.size()];
                // 循环当前行 这里最大列数采用头部数量
                for (int cellNum = firstCellNum; cellNum < heads.size(); cellNum++) {
                    String cellValue = getCellValue(row.getCell(cellNum));
                    cells[cellNum] = cellValue;
                }
                if (checkCells(cells)) {
                    cellList.add(cells);
                } else {
                    break;
                }
            }
            cellMap.put(sheet.getSheetName(), cellList);
        }
//        System.out.println("headMap >>> " + JSON.toJSONString(headMap));
//        System.out.println("cellMap >>> " + JSON.toJSONString(cellMap));
        return ImmutablePair.of(headMap, cellMap);
    }

    private static boolean checkCells(String[] cells) {
        for (int i = 0; i < cells.length; i++) {
            String cell = cells[i];
            if (StringUtils.isNotBlank(cell)) {
                return true;
            }
        }
        return false;
    }

    public static Workbook getWorkBook(String fileName) {
        // 创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            // 获取excel文件的io流
            InputStream is = new FileInputStream(fileName);

            // 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith(xls)) { // 2003
                workbook = new HSSFWorkbook(is);

            } else if (fileName.endsWith(xlsx)) { // 2007 及2007以上
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }


    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        // 把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        // 判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                // 数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                // 字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                // Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                // 公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK:
                // 空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR:
                // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }

        return cellValue;
    }

}
