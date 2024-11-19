package com.rjgj.zjpg.biz;


import com.rjgj.zjpg.entity.FileUpload;
import com.rjgj.zjpg.mapper.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

@Service
public class FileDeleteService {

    @Autowired
    private FileUploadRepository fileUploadRepository;

    public ResponseEntity<String> deleteFile(Long id) {
        Optional<FileUpload> optionalFileUpload = fileUploadRepository.findById(id);
        if (!optionalFileUpload.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }

        FileUpload fileUpload = optionalFileUpload.get();
        File file = new File(fileUpload.getFilePath());

        // 删除文件
        if (file.exists() && !file.delete()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete file from server");
        }

        // 删除数据库记录
        fileUploadRepository.deleteById(id);

        return ResponseEntity.ok("File deleted successfully");
    }
}
