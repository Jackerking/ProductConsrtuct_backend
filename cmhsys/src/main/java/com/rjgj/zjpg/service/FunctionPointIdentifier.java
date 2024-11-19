package com.rjgj.zjpg.service;

import com.rjgj.zjpg.model.FunctionalComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FunctionPointIdentifier {
    public static List<FunctionalComponent> identifyFunctionPoints(List<String> sentences) {
        List<FunctionalComponent> components = new ArrayList<>();
        Pattern ilfPattern = Pattern.compile(".*添加|创建|维护|删除.*数据.*", Pattern.CASE_INSENSITIVE);
        Pattern eiPattern = Pattern.compile(".*输入.*", Pattern.CASE_INSENSITIVE);
        Pattern eoPattern = Pattern.compile(".*输出.*", Pattern.CASE_INSENSITIVE);
        Pattern eqPattern = Pattern.compile(".*查询.*", Pattern.CASE_INSENSITIVE);
        Pattern eifPattern = Pattern.compile(".*添加管理员.*", Pattern.CASE_INSENSITIVE);


//        double ilfWeight = 10;
//        double eiWeight = 4;
//        double eoWeight = 5;
//        double eqWeight = 4;
//        double eifWeight =7;

        for (String sentence : sentences) {
            Matcher eifMatcher = eifPattern.matcher(sentence);
            if (eifMatcher.find()) {
                components.add(new FunctionalComponent("EIF", 3,"高", "新增", sentence));
            }
            Matcher ilfMatcher = ilfPattern.matcher(sentence);
            if (ilfMatcher.find()) {
                components.add(new FunctionalComponent("ILF",  2, "低", "新增", sentence));
            }
            Matcher eiMatcher = eiPattern.matcher(sentence);
            if (eiMatcher.find()) {
                components.add(new FunctionalComponent("EI",  5, "低", "新增", sentence));
            }
            Matcher eoMatcher = eoPattern.matcher(sentence);
            if (eoMatcher.find()) {
                components.add(new FunctionalComponent("EO",  2, "低", "新增", sentence));
            }
            Matcher eqMatcher = eqPattern.matcher(sentence);
            if (eqMatcher.find()) {
                components.add(new FunctionalComponent("EQ",  1, "中", "新增", sentence));
            }
        }
        return components;
    }
}