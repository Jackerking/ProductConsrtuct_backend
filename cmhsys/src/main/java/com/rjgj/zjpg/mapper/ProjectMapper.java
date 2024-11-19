package com.rjgj.zjpg.mapper;

import com.rjgj.zjpg.entity.Project;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ProjectMapper {
    // 创建项目
    @Insert("insert into t_project(projectName,id, projectTime,userId) values(#{projectName}, #{id}, #{projectTime},#{userId})")
    int addProject(Project project);
    //查询会议
    @Select("select * from t_project where projectName = #{projectName}")
    Project findByProjectName(String projectName);
    //更新项目
    @Update("update t_project set RSK=#{RSK},totalCost=#{totalCost} where projectId = #{projectId}")
    int updateProjectRSK(@Param("projectId") int projectId,@Param("RSK") float RSK,@Param("totalCost") double totalCost);
    // 根据用户ID查询项目列表
    @Select("SELECT * from t_project where userId = #{userId}")
    List<Project> findProjectsByUserId(int userId);
    // 根据项目名称删除项目
    @Delete("delete from t_project where projectName = #{projectName}")
    int deleteByProjectName(String projectName);
    @Select("select * from t_project WHERE projectName LIKE CONCAT('%', #{projectName}, '%')")
    List<Project> searchProjectsByName(@Param("projectName") String projectName);
    @Update("update t_project set filePath=#{filePath} where projectId = #{projectId}")
    int updateProjectfilePath(@Param("projectId") int projectId,@Param("filePath") String filePath);
    @Update("update t_project set unadjustedFunctionPoints=#{unadjustedFunctionPoints},adjustedFunctionPoints=#{adjustedFunctionPoints},EI=#{EI},EO=#{EO},EQ=#{EQ},ILF=#{ILF},EIF=#{EIF} where projectId = #{projectId}")
    int updateProject(@Param("projectId") int projectId,@Param("unadjustedFunctionPoints") Double unadjustedFunctionPoints,@Param("adjustedFunctionPoints") Double adjustedFunctionPoints,@Param("EI") int EI,@Param("EO") int EO,@Param("EQ") int EQ,@Param("ILF") int ILF,@Param("EIF") int EIF);

}
