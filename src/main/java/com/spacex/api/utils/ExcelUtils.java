package com.spacex.api.utils;




/**
 * @author Mahanthesh
 *
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    public static DataFormatter formatter = new DataFormatter();

    public static String[] readExcel(String fileName, String sheetName, String methodName) throws IOException {
        URL fileUrl = ExcelUtils.class.getResource(fileName);
        File file = new File(fileName);
        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = null;
        String fileExtensionName = fileName.substring(fileName.indexOf("."));
        if (fileExtensionName.equals(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        Sheet sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        Row rowFirst = sheet.getRow(0); // Added UST
        int columnCount = rowFirst.getLastCellNum(); // Added UST
        Boolean existLoop = false;
        String[] testDataDBData = new String[columnCount];
        String cellTestCaseName;

        for (int i = 1; i < rowCount + 1; i++) {
            Row row = sheet.getRow(i);
            cellTestCaseName = formatter.formatCellValue(row.getCell(0));
            if (cellTestCaseName.equals(methodName)) {
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    testDataDBData[j] = formatter.formatCellValue(row.getCell(j + 1));
                }
                existLoop = true;
                break;

            } else {
                existLoop = false;
            }

            if (existLoop)
                break;
        }

        return testDataDBData;
    }

}