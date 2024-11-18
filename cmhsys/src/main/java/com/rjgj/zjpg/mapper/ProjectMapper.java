package com.rjgj.zjpg.mapper;

import com.rjgj.zjpg.entity.Project;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ProjectMapper {
    // 创建项目
    @Insert("insert into t_project(projectName,id, projectTime) values(#{projectName}, #{id}, #{projectTime})")
    int addProject(Project project);
    //查询会议
    @Select("select * from t_project where projectName = #{projectName}")
    Project findByProjectName(String projectName);
    //更新项目
    @Update("update t_project set RSK=#{RSK},totalCost=#{totalCost} where projectId = #{projectId}")
    int updateProjectRSK(@Param("projectId") int projectId,@Param("RSK") float RSK,@Param("totalCost") double totalCost);
}
