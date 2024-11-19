package com.rjgj.zjpg.biz;

import com.rjgj.zjpg.mapper.FCmapper;
import com.rjgj.zjpg.model.FunctionalComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FCBiz {
    @Autowired
    private FCmapper fcMapper;
    public List<FunctionalComponent> getFCList(){
        return  fcMapper.selectFC();
    }
    public  boolean addFC(FunctionalComponent fc){
        return fcMapper.insertFC(fc)>0;
    }
    public boolean updateFC(FunctionalComponent fc){
        return fcMapper.updateFC(fc)>0;
    }
    public List<FunctionalComponent> searchFC(String keyword) {
        return fcMapper.searchFC(keyword);
    }
    public List<FunctionalComponent> getFC1bycg(String cg) {
        return fcMapper.selectFC1Bycg(cg);
    }
    public boolean removeFC(int id){
        return fcMapper.deleteFCById(id)>0;
    }
    public FunctionalComponent getFC2(int id){
        return  fcMapper.selectFC2(id);
    }
}
