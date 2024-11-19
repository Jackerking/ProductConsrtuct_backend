package com.rjgj.zjpg.biz;


import com.rjgj.zjpg.entity.FileUpload;
import com.rjgj.zjpg.mapper.FileUploadRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class FileViewService {

    @Autowired
    private FileUploadRepository fileUploadRepository;

    public ResponseEntity<String> viewFile(Long id) {
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(id);
        if (!optionalFileUpload.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }

        FileUpload fileUpload = optionalFileUpload.get();
        File file = new File(fileUpload.getFilePath());

        try (FileInputStream fis = new FileInputStream(file)) {
            if (fileUpload.getFileType().contains("wordprocessingml")) {
                // 查看 Word 文件
                try (XWPFDocument doc = new XWPFDocument(fis)) {
                    StringBuilder content = new StringBuilder();
                    doc.getParagraphs().forEach(p -> content.append(p.getText()).append("\n"));
                    return ResponseEntity.ok(content.toString());
                }
            } else if (fileUpload.getFileType().contains("spreadsheetml")) {
                // 查看 Excel 文件
                Workbook workbook = WorkbookFactory.create(fis);
                StringBuilder content = new StringBuilder();
                Sheet sheet = workbook.getSheetAt(0);
                for (Row row : sheet) {
                    for (Cell cell : row) {
                        content.append(cell.toString()).append("\t");
                    }
                    content.append("\n");
                }
                workbook.close();
                return ResponseEntity.ok(content.toString());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Unsupported file type");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error reading file: " + e.getMessage());
        }
    }
    public ResponseEntity<String> getFileDownloadUrl(Long id) {
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(id);
        if (!optionalFileUpload.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }

        FileUpload fileUpload = optionalFileUpload.get();
        return ResponseEntity.ok(fileUpload.getFilePath());
    }
}
