package com.rjgj.zjpg.controller;

import com.rjgj.zjpg.biz.CostStandardBiz;
import com.rjgj.zjpg.biz.FactorBiz;
import com.rjgj.zjpg.biz.ProjectBiz;
import com.rjgj.zjpg.entity.CostStandard;
import com.rjgj.zjpg.entity.Factor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Factor")
public class FactorController {
    @Autowired
    private FactorBiz biz;
    @Autowired
    private ProjectBiz projectBiz;


    @RequestMapping("/find")
    public Map<String, Object> findStdFactors(@RequestParam String factor_type, @RequestParam int stdId) {
        List<Factor> list = biz.getFactors(factor_type,stdId);
        Map map = new HashMap();
        map.put("isOk", true);
        map.put("Factors", list);
        map.put("msg", "查询成功");
        return map;
    }
    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestBody int stdId){
        System.out.println(stdId);
        Map map = new HashMap();
        if(biz.deleteFactors(stdId)){
            map.put("isOk",true);
            map.put("msg","删除成功");
        }else {
            map.put("isOk", false);
            map.put("msg", "删除失败");
        }
        return map;
    }


    @RequestMapping("/add")
    public Map<String, Object> add(@RequestBody Factor factor){
        Map map = new HashMap();
        if(biz.addFactor(factor)){
            map.put("isOk",true);
            map.put("msg","添加成功");
        }else{
            map.put("isOk",false);
            map.put("msg","添加失败");
        }
        return map;
    }

    @RequestMapping("/addFactors")
    public Map<String, Object> addFactors(@RequestBody List<Factor> factorList) {
        Map<String, Object> map = new HashMap<>();
        try {
            boolean isSaved = biz.addFactors(factorList);
            if (isSaved) {
                map.put("isOk", true);
                map.put("msg", "因子选项保存成功");
            } else {
                map.put("isOk", false);
                map.put("msg", "因子选项保存失败");
            }
        } catch (Exception e) {
            map.put("isOk", false);
            map.put("msg", "发生错误: " + e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("/updateProject")
    public Map<String, Object> updateProject(@RequestBody Map<String, Object> request) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 获取数据
            int projectId = (int) request.get("projectId");
            int S = (int) request.get("S");
            // 安全转换为 float
            float SF = convertToFloat(request.get("SF"));
            float BD = convertToFloat(request.get("BD"));
            float QR = convertToFloat(request.get("QR"));
            float AT = convertToFloat(request.get("AT"));
            float SL = convertToFloat(request.get("SL"));
            float DT = convertToFloat(request.get("DT"));
            int personnelCosts = (int) request.get("personnelCosts");
            int stdId = (int) request.get("stdId");
            float AE = calculateAdjustedEffort(S, personnelCosts, SF, BD, QR, AT, SL, DT);
            projectBiz.updateProjectAEAndPersonelCosts(projectId, AE, personnelCosts, stdId);

            // 你的逻辑代码
            map.put("status", true);
        } catch (Exception e) {
            map.put("status", false);
            map.put("message", "处理请求时发生错误: " + e.getMessage());
        }

        return map;
    }



    private float calculateAdjustedEffort(int S, int personnelCosts, float SF, float BD, float QR, float AT, float SL, float DT) {
        return S / (float) personnelCosts * SF * BD * QR * AT * SL * DT;
    }

    // 辅助方法：转换为 Float 类型
    private float convertToFloat(Object value) {
        if (value == null) {
            return 0.0f; // 默认值
        }
        if (value instanceof Integer) {
            return ((Integer) value).floatValue(); // 如果是 Integer，转为 Float
        }
        try {
            return Float.parseFloat(value.toString()); // 尝试将其他类型转换为 float
        } catch (NumberFormatException e) {
            return 0.0f; // 如果转换失败，返回默认值
        }
    }


}
