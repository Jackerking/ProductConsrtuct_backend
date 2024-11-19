package com.rjgj.zjpg.biz;

import com.rjgj.zjpg.entity.Project;
import com.rjgj.zjpg.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    // 根据用户ID查询项目列表
    public List<Project> getProjectsByUserId(int userId) {
        return projectmapper.findProjectsByUserId(userId);
    }
    // 删除项目
    public boolean deleteProjectByName(String projectName) {
        int rows = projectmapper.deleteByProjectName(projectName);
        return rows > 0; // 返回是否成功删除
    }
    public List<Project> searchProjectsByName(String projectName) {
        return projectmapper.searchProjectsByName(projectName);
    }

}
