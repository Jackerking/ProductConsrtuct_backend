package com.rjgj.zjpg.mapper;

import com.rjgj.zjpg.entity.BasicRate;
import com.rjgj.zjpg.entity.Province;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface BasicRateMapper {
    @Select("select * from basic_rate ")
    List<BasicRate>getBasicRate();

    // 查询所有省份及对应年份
    @Select("SELECT city, GROUP_CONCAT(DISTINCT year ORDER BY year) AS years " +
            "FROM basic_rate GROUP BY city")
    List<Province> getProvinces();


    // 获取所有的省份和年份数据
    @Select("SELECT city, year FROM basic_rate")
    List<BasicRate> getAllBasicRates();

    // 根据省份和年份查询 cost
    @Select("SELECT cost FROM basic_rate WHERE city = #{city} AND year = #{year}")
    double getCost(@Param("city") String province, @Param("year") int year);


}
