package com.rjgj.zjpg.controller;

import com.rjgj.zjpg.biz.BasicRateBiz;
import com.rjgj.zjpg.entity.BasicRate;
import com.rjgj.zjpg.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/basicRate")
public class BasicRateController {
    @Autowired
    private BasicRateBiz basicRateBiz;
    @RequestMapping("/getAll")
    private List<BasicRate> getAll(){
        return basicRateBiz.getBasicRate();
    }

    // 获取所有省份及年份
    @GetMapping("/getProvinces")
    public List<Province> getProvinces() {
        return basicRateBiz.getProvinces();
    }

    // 根据省份和年份查询 cost
    @GetMapping("/getCost")
    public double getCost(@RequestParam String province, @RequestParam int year) {
        return basicRateBiz.getCost(province, year);
    }

    @GetMapping("/getProvincesWithYears")
    public List<Province> getProvincesWithYears() {
        return basicRateBiz.getProvincesWithYears();
    }
}
