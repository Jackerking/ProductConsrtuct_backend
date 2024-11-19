package com.rjgj.zjpg.controller;


import com.rjgj.zjpg.biz.FileDeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delete")
public class FileDeleteController {

    @Autowired
    private FileDeleteService fileDeleteService;

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        return fileDeleteService.deleteFile(id);
    }
}
