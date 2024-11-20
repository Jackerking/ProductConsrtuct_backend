package com.rjgj.zjpg.service;

import java.util.ArrayList;
import java.util.List;

public class TextPreprocessor {
    public static List<String> preprocessText(List<String> paragraphs) {
        List<String> sentences = new ArrayList<>();
        for (String paragraph : paragraphs) {
            String[] sentenceArray = paragraph.split("\\.\\s*");
            for (String sentence : sentenceArray) {
                if (!sentence.trim().isEmpty()) {
                    sentences.add(sentence.trim());
                }
            }
        }
        return sentences;
    }
}
