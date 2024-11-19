package com.rjgj.zjpg.controller;

import com.rjgj.zjpg.biz.FileViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/view")
public class FileViewController {

    @Autowired
    private FileViewService fileViewService;

    @GetMapping("/{id}")
    public ResponseEntity<String> viewFile(@PathVariable Long id) {
        return fileViewService.viewFile(id);
    }
    @GetMapping("/download/url/{id}")
    public ResponseEntity<String> getFileDownloadUrl(@PathVariable Long id) {
        return fileViewService.getFileDownloadUrl(id);
    }
}