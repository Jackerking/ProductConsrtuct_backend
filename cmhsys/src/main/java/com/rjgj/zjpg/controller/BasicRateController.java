package com.rjgj.zjpg.controller;

import com.rjgj.zjpg.biz.BasicRateBiz;
import com.rjgj.zjpg.entity.BasicRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
