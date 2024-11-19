package com.rjgj.zjpg.controller;


import com.rjgj.zjpg.biz.FileUploadService;
import com.rjgj.zjpg.entity.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    // 上传 Word 文件
    @PostMapping("/upload/word")
    public ResponseEntity<?> uploadWordFile(@RequestParam("file") MultipartFile file,@RequestParam int projectId) {
        return fileUploadService.saveFile(file, "word",projectId);
    }


    // 上传 Excel 文件
    @PostMapping("/upload/excel")
    public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file,@RequestParam int projectId) {
        return fileUploadService.saveFile(file, "excel",projectId);
    }

    // 获取所有文件列表
    @GetMapping("/files")
    public ResponseEntity<List<FileUpload>> listFiles() {
        return ResponseEntity.ok(fileUploadService.listAllFiles());
    }


}