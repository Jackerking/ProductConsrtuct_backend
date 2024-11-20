package com.rjgj.zjpg.service;

import com.rjgj.zjpg.model.FunctionalComponent;
import com.rjgj.zjpg.model.Functionalpoint;
import com.rjgj.zjpg.model.GscPt;

import java.util.ArrayList;
import java.util.List;

public class FunctionPointAdjustmentService {
    private List<FunctionalComponent> components;

    public FunctionPointAdjustmentService() {
        this.components = new ArrayList<>();
    }

    public void addComponent(FunctionalComponent component, Functionalpoint fp) {
        components.add(component);
        component.calculateUnadjustedFunctionPoints(fp);
    }

    public double calculateTotalUnadjustedFunctionPoints(FunctionalComponent fc) {
        double total = fc.getAdjustedFunctionPoints();
        return total;
    }

    public double calculateTotalAdjustedFunctionPoints(FunctionalComponent fc,Functionalpoint fp,GscPt gp,String scaleChangeFactor) {
        double total = fc.calculateAdjustedFunctionPoints(fc,fp,gp,scaleChangeFactor);
        return total;
    }

}
