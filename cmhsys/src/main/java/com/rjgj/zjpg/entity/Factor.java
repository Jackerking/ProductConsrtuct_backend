package com.rjgj.zjpg.entity;

public class Factor {
    private String factor_type;
    private int stdId;
    private String factor_name;
    private float factor_value;

    public Factor() {
    }

    public String getFactor_type() {
        return factor_type;
    }

    public void setFactor_type(String factor_type) {
        this.factor_type = factor_type;
    }

    public int getStdId() {
        return stdId;
    }

    public void setStdId(int stdId) {
        this.stdId = stdId;
    }

    public String getFactor_name() {
        return factor_name;
    }

    public void setFactor_name(String factor_name) {
        this.factor_name = factor_name;
    }

    public float getFactor_value() {
        return factor_value;
    }

    public void setFactor_value(float factor_value) {
        this.factor_value = factor_value;
    }
}
