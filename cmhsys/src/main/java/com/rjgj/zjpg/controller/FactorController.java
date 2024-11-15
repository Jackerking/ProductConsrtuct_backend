package com.rjgj.zjpg.controller;

import com.rjgj.zjpg.biz.CostStandardBiz;
import com.rjgj.zjpg.biz.FactorBiz;
import com.rjgj.zjpg.entity.CostStandard;
import com.rjgj.zjpg.entity.Factor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Factor")
public class FactorController {
    @Autowired
    private FactorBiz biz;

    @RequestMapping("/find")
    public Map<String, Object> findStdFactors(@RequestParam String factor_type, @RequestParam int stdId) {
        List<Factor> list = biz.getFactors(factor_type,stdId);
        Map map = new HashMap();
        map.put("isOk", true);
        map.put("Factors", list);
        map.put("msg", "查询成功");
        return map;
    }
}
