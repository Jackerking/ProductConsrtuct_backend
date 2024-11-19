package com.rjgj.zjpg.biz;

import com.rjgj.zjpg.entity.BasicRate;
import com.rjgj.zjpg.mapper.BasicRateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
