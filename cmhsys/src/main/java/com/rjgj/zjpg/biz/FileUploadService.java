package com.rjgj.zjpg.biz;


import com.rjgj.zjpg.entity.FileUpload;
import com.rjgj.zjpg.mapper.FileUploadRepository;
import com.rjgj.zjpg.mapper.ProjectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FileUploadService {

    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Autowired
    private ProjectMapper projectmapper;
    public FileUploadService(){

    }

    // 保存文件（支持 Word 和 Excel）
    public ResponseEntity<?> saveFile(MultipartFile file, String fileType, int projectId) {
        try {
            // 检查文件类型
            String contentType = file.getContentType();
            boolean isValidType = ("word".equals(fileType) && (
                    "application/vnd.openxmlformats-officedocument.wordprocessingml.document".equals(contentType) ||
                            "application/msword".equals(contentType)))
                    || ("excel".equals(fileType) && (
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentType) ||
                            "application/vnd.ms-excel".equals(contentType)));

            if (!isValidType) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid file type for " + fileType);
            }

            // 动态获取项目的根目录
            String uploadDir = "C:\\Users\\93229\\Desktop\\aaaaa\\ProductConsrtuct_frontend\\public";

            // 确保文件夹存在
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 构建文件路径
            String fileName = file.getOriginalFilename();
            if (fileName == null || fileName.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File name is missing");
            }

            String filePath = uploadDir + File.separator + fileName;
            File targetFile = new File(filePath);

            // 检查文件是否已存在
            if (targetFile.exists()) {
                Optional<FileUpload> existingFile = fileUploadRepository.findByFileName(fileName);
                if (existingFile.isPresent()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body("File already exists: " + fileName);
                }
            }

            // 保存文件到服务器
            file.transferTo(targetFile);

            // 保存文件元数据到数据库
            FileUpload fileUpload = new FileUpload(fileName, filePath, contentType, file.getSize(), LocalDateTime.now());
            fileUploadRepository.save(fileUpload);
            projectmapper.updateProjectfilePath(projectId,filePath);

            return ResponseEntity.ok(fileUpload);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving file: " + e.getMessage());
        }
    }
    public List<FileUpload> listAllFiles() {
        return fileUploadRepository.findAll();
    }


}