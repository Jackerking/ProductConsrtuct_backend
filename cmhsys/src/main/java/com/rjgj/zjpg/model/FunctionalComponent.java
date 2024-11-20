package com.rjgj.zjpg.model;

public class FunctionalComponent {
    private Integer id;
    private String category; // ILF, EIF, EI, EO, EQ
    private double unadjustedFunctionPoints;
    private String reuseLevel;
    private String modificationType;
    private String triggeringSentence;
    private double adjustedFunctionPoints;

    public FunctionalComponent() {
    }
    public FunctionalComponent(String category, double unadjustedFunctionPoints, String reuseLevel, String modificationType, String triggeringSentence) {
        this.category = category;
        this.unadjustedFunctionPoints = unadjustedFunctionPoints;
        this.reuseLevel = reuseLevel;
        this.modificationType = modificationType;
        this.triggeringSentence = triggeringSentence;
    }
    public FunctionalComponent(Integer id, String category, double unadjustedFunctionPoints, String reuseLevel, String modificationType, String triggeringSentence, double adjustedFunctionPoints) {
        this.adjustedFunctionPoints = adjustedFunctionPoints;
        this.category = category;
        this.id = id;
        this.modificationType = modificationType;
        this.reuseLevel = reuseLevel;
        this.triggeringSentence = triggeringSentence;
        this.unadjustedFunctionPoints = unadjustedFunctionPoints;
    }

    public Integer getId() {
        return id;
    }

    public String getModificationType() {
        return modificationType;
    }

    public String getReuseLevel() {
        return reuseLevel;
    }

    public String getTriggeringSentence() {
        return triggeringSentence;
    }

    public double getUnadjustedFunctionPoints() {
        return unadjustedFunctionPoints;
    }

    public String getCategory() {
        return category;
    }

    public double getAdjustedFunctionPoints() {
        return adjustedFunctionPoints;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setUnadjustedFunctionPoints(double unadjustedFunctionPoints) {
        this.unadjustedFunctionPoints = unadjustedFunctionPoints;
    }

    public void setReuseLevel(String reuseLevel) {
        this.reuseLevel = reuseLevel;
    }

    public void setModificationType(String modificationType) {
        this.modificationType = modificationType;
    }

    public void setTriggeringSentence(String triggeringSentence) {
        this.triggeringSentence = triggeringSentence;
    }

    public void setAdjustedFunctionPoints(double adjustedFunctionPoints) {
        this.adjustedFunctionPoints = adjustedFunctionPoints;
    }

    public double calculateUnadjustedFunctionPoints(Functionalpoint fp) {
        double modificationFactor = getModificationTypeFactor(modificationType);
        double reuseFactor = getReuseLevelFactor(reuseLevel);
        double adjustedFunctionPoints1 = 0;
//        System.out.println(unadjustedFunctionPoints * modificationFactor * reuseFactor);
        adjustedFunctionPoints1 = unadjustedFunctionPoints * modificationFactor * reuseFactor;
        System.out.println(adjustedFunctionPoints);
        if(this.category.equals("EIF")){
            adjustedFunctionPoints1 = fp.getEifWeight() * adjustedFunctionPoints1;
        } else if (this.category.equals("EO")) {
            adjustedFunctionPoints1 = fp.getEoWeight() * adjustedFunctionPoints1;
        } else if (this.category.equals("EQ")) {
            adjustedFunctionPoints1 = fp.getEqWeight() * adjustedFunctionPoints1;
        } else if (this.category.equals("EI")) {
            adjustedFunctionPoints1 = fp.getEiWeight() * adjustedFunctionPoints1;
        } else if (this.category.equals("ILF")) {
            adjustedFunctionPoints1 = fp.getIlfWeight() * adjustedFunctionPoints1;
        }
        this.adjustedFunctionPoints = adjustedFunctionPoints1;
        return this.adjustedFunctionPoints;
    }

    private double getModificationTypeFactor(String modificationType) {
        switch (modificationType) {
            case "新增":
                return 1.00;
            case "修改":
                return 0.80;
            case "删除":
                return 0.20;
            default:
                return 1.00;
        }
    }

    private double getReuseLevelFactor(String reuseLevel) {
        switch (reuseLevel) {
            case "低":
                return 1.00;
            case "中":
                return 0.67;
            case "高":
                return 0.33;
            default:
                return 1.00;
        }
    }
    public double calculateAdjustedFunctionPoints(FunctionalComponent fc,Functionalpoint fp,GscPt gp,String scaleChangeFactor1){
        double adjustedFunctionPoints1 = 0;
        adjustedFunctionPoints1 = fc.calculateUnadjustedFunctionPoints(fp);
        double sum = 0;
        sum = (gp.getCommunication()+gp.getOnlineDataEntry()+gp.getDistributedProcessing()+gp.getComplexity()
                +gp.getPerformance()+gp.getReusability()+gp.getConfiguration()+gp.getEaseOfInstallation()
                +gp.getTransactionFrequency()+gp.getEaseOfOperation()+gp.getOnlineDataEntry()+gp.getCrossPlatform()
                +gp.getEndUserUsageRate()+gp.getFlexibility())*0.01+0.65;
        double  scaleChangeFactor = 0;
        if(scaleChangeFactor1.equals("估算早期")){
            scaleChangeFactor = 1.39;
        } else if (scaleChangeFactor1.equals("估算中期")) {
            scaleChangeFactor = 1.21;
        } else if (scaleChangeFactor1.equals("估算晚期")) {
            scaleChangeFactor = 1.10;
        }else {
            scaleChangeFactor = 1.00;
        }
        this.adjustedFunctionPoints = adjustedFunctionPoints1 * sum * scaleChangeFactor;
        return this.adjustedFunctionPoints;
    }
    @Override
    public String toString() {
        return  "id:"+id+",Category: " + category + ", Triggering Sentence: '" + triggeringSentence + "', Unadjusted FP: " + unadjustedFunctionPoints + ", Reuse Level: " + reuseLevel + ", Modification Type: " + modificationType + ", Adjusted FP: " + this.adjustedFunctionPoints;
    }
}
