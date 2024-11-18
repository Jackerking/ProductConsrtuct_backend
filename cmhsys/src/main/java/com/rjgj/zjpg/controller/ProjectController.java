package com.rjgj.zjpg.controller;

import com.rjgj.zjpg.biz.ProjectBiz;
import com.rjgj.zjpg.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectBiz projectbiz;
    //创建项目
    @PostMapping("/create")
    public Map<String, Object> addProject(@RequestBody Project project) {
        boolean isOk = projectbiz.addProject(project);
        Map<String, Object> map = new HashMap<>();
        map.put("isOk", isOk);
        map.put("msg", isOk ? "添加成功" : "添加失败");
        if(isOk){
       Project svaedProject=projectbiz.findByProjectName(project.getProjectName());
            map.put("project", svaedProject);
        }
        return map;
    }
    //通过projectName查找
    @PostMapping("/find")
    public Map findProject(@RequestBody Map<String,String> requestBody){
        String projectName = requestBody.get("projectName");
        Project project=projectbiz.findByProjectName(projectName);
        Map map = new HashMap();
        map.put("isOk",true);
        map.put("project",project);
        return map;
    }
    //插入RSK
    @PostMapping("/RSK")
    public Map updateProjectRSK(@RequestParam int projectId, @RequestParam float RSK,@RequestParam double totalCost) {
        System.out.println(RSK);
            boolean success = projectbiz.updateProjectRSK(projectId,RSK,totalCost);
            Map map = new HashMap();
            if (success) {
                map.put("isOk",true);
            }
             return map;
    }

}
