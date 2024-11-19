package com.rjgj.zjpg.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rjgj.zjpg.biz.FCBiz;
import com.rjgj.zjpg.biz.ProjectBiz;
import com.rjgj.zjpg.model.FunctionalComponent;
import com.rjgj.zjpg.model.Functionalpoint;
import com.rjgj.zjpg.model.GscPt;
import com.rjgj.zjpg.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/fc")
public class FCController {
    @Autowired
    private FCBiz biz;
    @Autowired
    private ProjectBiz projectBiz;
    @Autowired
    private ExcelDocumentParser excelParser;
    // 在 CourseController 类中修改 findALl 方法
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map<String, Object> findALl() {
        List<FunctionalComponent> fc_list = biz.getFCList();
        Map<String, Object> map = new HashMap<>();
        map.put("isok", true);
        System.out.println(fc_list);
        map.put("fc", fc_list);
        map.put("msg", "查询成功");
        return map;
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> addFC(@RequestParam("category") String category,
                                     @RequestParam("unadjustedFunctionPoints") double unadjustedFunctionPoints,
                                     @RequestParam("reuseLevel") String reuseLevel,
                                     @RequestParam("modificationType") String modificationType,
                                     @RequestParam("triggeringSentence") String triggeringSentence,
                                     @RequestParam("fp") String fpJson,
                                     @RequestParam("gp") String gpJson,
                                     @RequestParam("scaleChangeFactor1") String scaleChangeFactor1,
                                     @RequestParam("totalUnadjusted") Double totalUnadjusted,
                                     @RequestParam("totalAdjusted") Double totalAdjusted)throws JsonProcessingException {
        //反序列化
        ObjectMapper mapper = new ObjectMapper();
        Functionalpoint fp = mapper.readValue(fpJson, Functionalpoint.class);
        GscPt gp = mapper.readValue(gpJson, GscPt.class);

        FunctionalComponent fc = new FunctionalComponent();
        fc.setCategory(category);
        fc.setUnadjustedFunctionPoints(unadjustedFunctionPoints);
        fc.setReuseLevel(reuseLevel);
        fc.setModificationType(modificationType);
        fc.setTriggeringSentence(triggeringSentence);
        double adjustedFunctionPoints = fc.calculateUnadjustedFunctionPoints(fp);
        fc.setAdjustedFunctionPoints(adjustedFunctionPoints);
        boolean isAdded = biz.addFC(fc);
        totalUnadjusted = totalUnadjusted + fc.calculateUnadjustedFunctionPoints(fp);
        totalAdjusted = totalAdjusted + fc.calculateAdjustedFunctionPoints(fc,fp,gp,scaleChangeFactor1);
        Map<String, Object> map = new HashMap<>();
        map.put("totalUnadjusted", totalUnadjusted);
        map.put("totalAdjusted",totalAdjusted);
        map.put("isok", isAdded);
        map.put("msg", isAdded ? "添加成功" : "添加失败");
        return map;
    }
    @RequestMapping(value = "/addweight", method = RequestMethod.POST)
    public Map<String, Object> addFP(@RequestParam("fp") String fpJson,
                                     @RequestParam("gp") String gpJson,
                                     @RequestParam("scaleChangeFactor1") String scaleChangeFactor,
                                     @RequestParam("filePath") String filePath,
                                     @RequestParam("projectId") int projectId
                                     ) throws IOException {
        //反序列化
        ObjectMapper mapper = new ObjectMapper();
        Functionalpoint fp = mapper.readValue(fpJson, Functionalpoint.class);
        GscPt gp = mapper.readValue(gpJson, GscPt.class);

        //计数
        int n1=0,n2=0,n3=0,n4=0,n5=0;


        //@RequestParam("filePath") String filePath


        // 根据文件扩展名调用不同的解析方法
        if (filePath.endsWith(".xlsx")) {
            // 处理Excel文件
            List<String> data = excelParser.parseExcelDocument(filePath);
            FunctionPointAdjustmentService analysisSystem = new FunctionPointAdjustmentService();
            double totalUnadjusted = 0;
            double totalAdjusted = 0;
            for (String rowData : data) {
                String[] values = rowData.split(",");
                FunctionalComponent fc = new FunctionalComponent(
                        values[0], // category
                        Double.parseDouble(values[1]), // unadjustedFunctionPoints
                        values[2], // reuseLevel
                        values[3], // modificationType
                        values[4]// triggeringSentence
                );
                double adjustedFunctionPoints = fc.calculateUnadjustedFunctionPoints(fp);
                fc.setAdjustedFunctionPoints(adjustedFunctionPoints);
                boolean isAdded = biz.addFC(fc);
                // Calculate adjusted function points
                analysisSystem.addComponent(fc, fp);
                if(fc.getCategory().equals("EIF")){
                    n1++;
                } else if (fc.getCategory().equals("EO")) {
                    n2++;
                } else if (fc.getCategory().equals("EQ")) {
                    n3++;
                } else if (fc.getCategory().equals("EI")) {
                    n4++;
                } else if (fc.getCategory().equals("ILF")) {
                    n5++;
                }
                totalUnadjusted = totalUnadjusted + analysisSystem.calculateTotalUnadjustedFunctionPoints(fc);
                totalAdjusted = totalAdjusted + analysisSystem.calculateTotalAdjustedFunctionPoints(fc, fp, gp, scaleChangeFactor);
            }
            Map<String, Object> map = new HashMap<>();
            boolean isAdded = true;

            projectBiz.updateProject(projectId,totalUnadjusted,totalAdjusted,n4,n2,n3,n5,n1);
            map.put("EIFCount", n1);
            map.put("EOCount", n2);
            map.put("EQCount", n3);
            map.put("EICount", n4);
            map.put("ILFCount", n5);
            map.put("totalUnadjusted", totalUnadjusted);
            map.put("totalAdjusted", totalAdjusted);
            System.out.println(totalUnadjusted);
            map.put("isok", isAdded);
            return map;
            } else {
            Map<String, Object> map = new HashMap<>();
            try {
                List<String> paragraphs = WordDocumentParser.parseWordDocument(filePath);
                List<String> sentences = TextPreprocessor.preprocessText(paragraphs);
                List<FunctionalComponent> components = FunctionPointIdentifier.identifyFunctionPoints(sentences);
                FunctionPointAdjustmentService analysisSystem = new FunctionPointAdjustmentService();
                double totalUnadjusted = 0;
                double totalAdjusted = 0;
                for (FunctionalComponent component : components) {
                    analysisSystem.addComponent(component, fp); // 这将计算adjustedFunctionPoints
                    if(component.getCategory().equals("EIF")){
                        n1++;
                    } else if (component.getCategory().equals("EO")) {
                        n2++;
                    } else if (component.getCategory().equals("EQ")) {
                        n3++;
                    } else if (component.getCategory().equals("EI")) {
                        n4++;
                    } else if (component.getCategory().equals("ILF")) {
                        n5++;
                    }
                    totalUnadjusted = totalUnadjusted + analysisSystem.calculateTotalUnadjustedFunctionPoints(component);
                    totalAdjusted = totalAdjusted + analysisSystem.calculateTotalAdjustedFunctionPoints(component, fp, gp, scaleChangeFactor);
                }
                for (FunctionalComponent component : components) {
                    // System.out.println(component);
                    biz.addFC(component); // 使用业务层方法添加功能点
                }
                System.out.println("Total Unadjusted Function Points: " + totalUnadjusted);
                System.out.println("Adjusted Function Points: " + totalAdjusted);
                boolean isAdded = true;
                System.out.println(11);
                projectBiz.updateProject(projectId,totalUnadjusted,totalAdjusted,n4,n2,n3,n5,n1);
                map.put("EIFCount", n1);
                map.put("EOCount", n2);
                map.put("EQCount", n3);
                map.put("EICount", n4);
                map.put("ILFCount", n5);
                map.put("totalUnadjusted", totalUnadjusted);
                map.put("totalAdjusted", totalAdjusted);
                System.out.println(totalUnadjusted);
                map.put("isok", isAdded);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return map;
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map update(@RequestParam("id") Integer id,
                      @RequestParam("category") String category,
                      @RequestParam("unadjustedFunctionPoints") double unadjustedFunctionPoints,
                      @RequestParam("reuseLevel") String reuseLevel,
                      @RequestParam("modificationType") String modificationType,
                      @RequestParam("fp") String fpJson,
                      @RequestParam("gp") String gpJson,
                      @RequestParam("scaleChangeFactor1") String scaleChangeFactor1,
                      @RequestParam("totalUnadjusted") Double totalUnadjusted,
                      @RequestParam("totalAdjusted") Double totalAdjusted)throws JsonProcessingException{
        FunctionalComponent fc1 = biz.getFC2(id);
        //反序列化
        ObjectMapper mapper = new ObjectMapper();
        Functionalpoint fp = mapper.readValue(fpJson, Functionalpoint.class);
        GscPt gp = mapper.readValue(gpJson, GscPt.class);

        FunctionalComponent fc = new FunctionalComponent();
        fc.setId(id);
        fc.setCategory(category);
        fc.setUnadjustedFunctionPoints(unadjustedFunctionPoints);
        fc.setReuseLevel(reuseLevel);
        fc.setModificationType(modificationType);
        double adjustedFunctionPoints = fc.calculateUnadjustedFunctionPoints(fp);
        fc.setAdjustedFunctionPoints(adjustedFunctionPoints);

        totalUnadjusted = totalUnadjusted - fc1.calculateUnadjustedFunctionPoints(fp) + fc.calculateUnadjustedFunctionPoints(fp);
        totalAdjusted = totalAdjusted - fc1.calculateAdjustedFunctionPoints(fc1,fp,gp,scaleChangeFactor1)
                + fc.calculateAdjustedFunctionPoints(fc,fp,gp,scaleChangeFactor1);

        boolean isok = biz.updateFC(fc);
        Map map = new HashMap();
        map.put("totalUnadjusted", totalUnadjusted);
        map.put("totalAdjusted",totalAdjusted);
        if (isok){
            map.put("isok", true);
            map.put("msg", "修改成功");
        }
        else {
            map.put("isok", false);
            map.put("msg", "修改失败");
        }
        return map;
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchFC(@RequestParam String keyword) {
        List<FunctionalComponent> searchResult = biz.searchFC(keyword);
        if (searchResult != null && !searchResult.isEmpty()) {
            return ResponseEntity.ok(searchResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/filter")
    public ResponseEntity<List<FunctionalComponent>> getFCbycg(@RequestParam String cg) {
        List<FunctionalComponent> fc = biz.getFC1bycg(cg);
        return ResponseEntity.ok(fc); // 使用 HttpStatus.OK
    }
    @RequestMapping("/del")
    public Map del(@RequestParam("id") Integer id,
                   @RequestParam("fp") String fpJson,
                   @RequestParam("gp") String gpJson,
                   @RequestParam("scaleChangeFactor1") String scaleChangeFactor1,
                   @RequestParam("totalUnadjusted") Double totalUnadjusted,
                   @RequestParam("totalAdjusted") Double totalAdjusted)throws JsonProcessingException{
        //反序列化
        ObjectMapper mapper = new ObjectMapper();
        Functionalpoint fp = mapper.readValue(fpJson, Functionalpoint.class);
        GscPt gp = mapper.readValue(gpJson, GscPt.class);

        System.out.println(totalUnadjusted);
        System.out.println(totalAdjusted);
        FunctionalComponent fc1 = biz.getFC2(id);
        boolean isok = biz.removeFC(id);
        totalUnadjusted = totalUnadjusted - fc1.calculateUnadjustedFunctionPoints(fp);
        totalAdjusted = totalAdjusted - fc1.calculateAdjustedFunctionPoints(fc1,fp,gp,scaleChangeFactor1);

        System.out.println(totalUnadjusted);
        System.out.println(totalAdjusted);
        Map map = new HashMap();
        map.put("totalUnadjusted", totalUnadjusted);
        map.put("totalAdjusted",totalAdjusted);

        if (isok){
            map.put("isok",true);
            map.put("msg","删除成功");
        }
        else {
            map.put("isok", false);
            map.put("msg", "删除失败");
        }
        return map;
    }

}
