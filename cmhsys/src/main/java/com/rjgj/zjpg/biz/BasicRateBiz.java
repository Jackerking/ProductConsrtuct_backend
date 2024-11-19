package com.rjgj.zjpg.biz;

import com.rjgj.zjpg.entity.BasicRate;
import com.rjgj.zjpg.entity.Province;
import com.rjgj.zjpg.mapper.BasicRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BasicRateBiz {
@Autowired
    private BasicRateMapper basicRateMapper;

    public BasicRateBiz(BasicRateMapper basicRateMapper) {
        this.basicRateMapper = basicRateMapper;
    }
    public List<BasicRate> getBasicRate(){
        return basicRateMapper.getBasicRate();
    }

    // 获取所有省份及年份
    public List<Province> getProvinces() {
        return basicRateMapper.getProvinces();
    }

    // 根据省份和年份查询 cost
    public double getCost(String province, int year) {
        return basicRateMapper.getCost(province, year);
    }

    public List<Province> getProvincesWithYears() {
        List<BasicRate> basicRates = basicRateMapper.getAllBasicRates();

        // 使用 Map 来存储城市和对应的年份集合
        Map<String, Set<Integer>> provinceYearsMap = new HashMap<>();

        for (BasicRate rate : basicRates) {
            provinceYearsMap
                    .computeIfAbsent(rate.getCity(), k -> new HashSet<>())
                    .add(rate.getYear());
        }

        // 转换 Map 为 List<Province>
        List<Province> provinces = new ArrayList<>();
        for (Map.Entry<String, Set<Integer>> entry : provinceYearsMap.entrySet()) {
            provinces.add(new Province(entry.getKey(), new ArrayList<>(entry.getValue())));
        }
        return provinces;
    }
}
