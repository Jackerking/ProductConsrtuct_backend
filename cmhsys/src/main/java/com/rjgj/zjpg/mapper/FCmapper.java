package com.rjgj.zjpg.mapper;

import com.rjgj.zjpg.model.FunctionalComponent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FCmapper {
    @Select("select * from t_funcom")
    List<FunctionalComponent> selectFC();
    @Insert("insert into t_funcom values (null,#{category},#{unadjustedFunctionPoints},#{reuseLevel},#{modificationType},#{triggeringSentence},#{adjustedFunctionPoints})")
    int insertFC(FunctionalComponent fc);
    @Update("update t_funcom set category=#{category},unadjustedFunctionPoints=#{unadjustedFunctionPoints},reuseLevel=#{reuseLevel},modificationType=#{modificationType},adjustedFunctionPoints=#{adjustedFunctionPoints} where id=#{id}")
    int updateFC(FunctionalComponent fc);
    @Select("SELECT * FROM t_funcom WHERE triggeringSentence LIKE CONCAT('%', #{keyword}, '%') ")
    List<FunctionalComponent> searchFC(@Param("keyword") String keyword);
    @Select("select * from t_funcom where category=#{category}")
    List<FunctionalComponent>selectFC1Bycg(@Param("category") String category);
    @Delete("delete from t_funcom where id=#{id}")
    int deleteFCById(int id);
    @Select("select * from t_funcom where id=#{id}")
    FunctionalComponent selectFC2(int id);
}
