package com.rjgj.zjpg.biz;


import com.rjgj.zjpg.entity.CostStandard;
import com.rjgj.zjpg.mapper.CostStandardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostStandardBiz {
    @Autowired
    private CostStandardMapper mapper;

    public CostStandardBiz() {
    }

    public CostStandard getCostStandard(int stdId) {
        if(mapper.getCostStandardById(stdId) !=null){
            return mapper.getCostStandardById(stdId);
        }else{
            return null;
        }
    }
    public List<CostStandard> getCostStandardList() {
        return this.mapper.selectCostStandard();
    }

    public boolean addCostStandard(CostStandard costStandard) {
        if (costStandard != null) {
            return mapper.insertCostStandard(costStandard);
        }
        return false;
    }


    public boolean updateCostStandard(CostStandard costStandard) {
        if(costStandard != null){
            return mapper.updateCostStandard(costStandard);
        }
        return false;
    }
    public boolean deleteCostStandard(CostStandard costStandard) {
        return mapper.deleteCostStandard(costStandard.getStdId());
    }
}
