package com.rjgj.zjpg.controller;

import com.rjgj.zjpg.biz.CostStandardBiz;
import com.rjgj.zjpg.entity.CostStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/CostStandard")

public class CostStandardController {
    @Autowired
    private CostStandardBiz biz;

    @RequestMapping("/list")
    public Map findAll() {
        List<CostStandard> list = biz.getCostStandardList();
        Map map = new HashMap();
        map.put("isOk", true);
        map.put("CostStandards", list);
        map.put("msg", "查询成功");
        return map;
    }

    @RequestMapping("/get")
    public Map getCostStandardById(@RequestParam int stdId) {
        Map map = new HashMap();
        CostStandard costStandard = biz.getCostStandard(stdId);
        map.put("isOk", true);
        map.put("CostStandard", costStandard);
        map.put("msg", "查询成功");
        return map;
    }

    @RequestMapping("/add")
    public Map<String, Object> add(@RequestBody CostStandard costStandard){
        System.out.println(costStandard.getStdName());
        System.out.println(costStandard.getpdr());
        Map map = new HashMap();
        if(biz.addCostStandard(costStandard)){
            map.put("isOk",true);
            map.put("msg","添加成功");
        }else{
            map.put("isOk",false);
            map.put("msg","添加失败");
        }
        return map;
    }


    //在添加标准后获取 stdId
    @RequestMapping("/addForStdId")
    public Map<String, Object> addForStdId(@RequestBody CostStandard costStandard) {
        Map<String, Object> map = new HashMap<>();
        try {
            // 添加标准
            boolean isAdded = biz.addCostStandard(costStandard);
            System.out.println(costStandard.getStdId());
            if (isAdded) {
                map.put("isOk", true);
                map.put("msg", "添加成功");
                // 返回插入后的 stdId
                map.put("stdId", costStandard.getStdId());
            } else {
                map.put("isOk", false);
                map.put("msg", "添加失败");
            }
        } catch (Exception e) {
            map.put("isOk", false);
            map.put("msg", "发生错误：" + e.getMessage());
        }
        return map;
    }

    @RequestMapping("/update")
    public Map<String, Object> update(@RequestBody CostStandard costStandard){
        System.out.println("Received costStandard: " + costStandard);
        System.out.println(costStandard.getStdName());
        System.out.println(costStandard.getStdId());
        System.out.println(costStandard.getType());
        Map map = new HashMap();
        if(biz.updateCostStandard(costStandard)){
            map.put("isOk",true);
            map.put("msg","修改成功");
        }else{
            map.put("isOk",false);
            map.put("msg","修改失败");
        }
        return map;
    }

    @RequestMapping("/delete")
    public Map<String, Object> delete(@RequestBody CostStandard costStandard){
        Map map = new HashMap();
        if(biz.deleteCostStandard(costStandard)){
            map.put("isOk",true);
            map.put("msg","删除成功");
        }else{
            map.put("isOk",false);
            map.put("msg","删除失败");
        }
        return map;
    }


//    @RequestMapping("/find")
//    public Map<String, Object> find(@RequestBody CostStandard costStandard){
//
//        Map map = new HashMap();
//        if(costStandard.getVideourl() != null){
//            map.put("isOk",true);
//            map.put("msg","删除成功");
//        }else{
//            map.put("isOk",false);
//            map.put("msg","删除失败");
//        }
//        return map;
//    }


//    @RequestMapping("/video")
//    public Map<String, Object> video(@RequestBody Course course){
//        Map map = new HashMap();
//        System.out.println(course.getCourseid());
//        Course dbcourse = biz.getcourse(course.getCourseid());
//        if(biz.getcourse(course.getCourseid()) != null){
//            map.put("isOk",true);
//            map.put("course",dbcourse);
//            map.put("msg","查找成功");
//        }else{
//            map.put("isOk",false);
//            map.put("msg","查找失败");
//        }
//        return map;
//    }


}
