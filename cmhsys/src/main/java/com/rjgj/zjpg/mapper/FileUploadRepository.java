package com.rjgj.zjpg.mapper;


import com.rjgj.zjpg.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {

    Optional<FileUpload> findByFileName(String fileName);

    // 新增方法：获取所有文件名
    List<FileUpload> findAll();
}