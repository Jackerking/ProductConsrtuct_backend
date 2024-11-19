package com.rjgj.zjpg.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordDocumentParser {
    public static List<String> parseWordDocument(String filePath) throws IOException {
        List<String> paragraphs = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                paragraphs.add(paragraph.getText());
            }
        }
        return paragraphs;
    }
}
