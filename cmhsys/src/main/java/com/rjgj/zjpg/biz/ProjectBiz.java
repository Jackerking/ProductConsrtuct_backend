package com.rjgj.zjpg.biz;

import com.rjgj.zjpg.entity.Project;
import com.rjgj.zjpg.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectBiz {
    @Autowired
    private ProjectMapper projectmapper;
    public ProjectBiz(){

    }
    public boolean addProject(Project project) {
        return projectmapper.addProject(project) > 0;
    }
    public Project findByProjectName(String projectName) {
        return projectmapper.findByProjectName(projectName);
    }
    public boolean updateProjectRSK( int projectId, float RSK,double totalCost) {
        // 调用 Mapper 层方法更新数据
        int rowsAffected = projectmapper.updateProjectRSK(projectId, RSK,totalCost);
        // 返回是否更新成功
        return rowsAffected > 0;
    }
}
