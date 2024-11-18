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

    public boolean addFactor(Factor factor) {
        if (factor!= null) {
            return mapper.insertFactor(factor);
        }
        return false;
    }

    public boolean addFactors(List<Factor> factorList) {
        try {
            // 调用 Mapper 层保存数据
            int insertedCount = mapper.batchInsertFactors(factorList);
            return insertedCount == factorList.size();  // 如果插入的记录数与提供的列表大小一致，则表示成功
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
