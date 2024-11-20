package com.rjgj.zjpg.service;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelDocumentParser {
    public List<String> parseExcelDocument(String filePath) throws IOException {
        List<String> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            // 跳过表头，从第二行开始处理数据
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    StringBuilder rowData = new StringBuilder();
                    for (Cell cell : row) {
                        // 检查单元格类型并相应地处理
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData.append(cell.getStringCellValue()).append(",");
                                break;
                            case NUMERIC:
                                if (DateUtil.isCellDateFormatted(cell)) {
                                    rowData.append(cell.getDateCellValue()).append(",");
                                } else {
                                    rowData.append(cell.getNumericCellValue()).append(",");
                                }
                                break;
                            case BOOLEAN:
                                rowData.append(cell.getBooleanCellValue()).append(",");
                                break;
                            default:
                                rowData.append("").append(",");
                                break;
                        }
                    }
                    // 移除最后一个逗号并添加到数据列表
                    data.add(rowData.toString().trim());
                }
            }
        }
        return data;
    }
}