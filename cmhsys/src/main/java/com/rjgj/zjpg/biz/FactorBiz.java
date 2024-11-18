package com.rjgj.zjpg.biz;

import com.rjgj.zjpg.entity.CostStandard;
import com.rjgj.zjpg.entity.Factor;
import com.rjgj.zjpg.mapper.FactorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactorBiz {

    @Autowired
    private FactorMapper mapper;

    public FactorBiz() {
    }

    public List<Factor> getFactors(String factor_type,int stdId) {
        if(mapper.getFactorsByTypeAndStdId(factor_type,stdId) != null){
            return mapper.getFactorsByTypeAndStdId(factor_type,stdId);
        }else{
            return null;
        }
    }
    public boolean deleteFactors(int stdId) {
        return mapper.deleteFactorsByStdId(stdId);
    }


}
