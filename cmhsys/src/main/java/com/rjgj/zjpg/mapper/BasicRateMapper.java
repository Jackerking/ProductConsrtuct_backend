package com.rjgj.zjpg.mapper;

import com.rjgj.zjpg.entity.BasicRate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BasicRateMapper {
    @Select("select * from basic_rate ")
    List<BasicRate>getBasicRate();
}
