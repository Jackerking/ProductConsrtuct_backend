package com.rjgj.zjpg.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Functionalpoint {
    @JsonProperty("ilfWeight")
    private double ilfWeight;
    @JsonProperty("eiWeight")
    private double eiWeight;
    @JsonProperty("eoWeight")
    private double eoWeight;
    @JsonProperty("eqWeight")
    private double eqWeight ;
    @JsonProperty("eifWeight")
    private double eifWeight;

    public void setEifWeight(double eifWeight) {
        this.eifWeight = eifWeight;
    }

    public void setEiWeight(double eiWeight) {
        this.eiWeight = eiWeight;
    }

    public void setEoWeight(double eoWeight) {
        this.eoWeight = eoWeight;
    }

    public void setEqWeight(double eqWeight) {
        this.eqWeight = eqWeight;
    }

    public void setIlfWeight(double ilfWeight) {
        this.ilfWeight = ilfWeight;
    }

    public double getEifWeight() {
        return eifWeight;
    }

    public double getEiWeight() {
        return eiWeight;
    }

    public double getEoWeight() {
        return eoWeight;
    }

    public double getEqWeight() {
        return eqWeight;
    }

    public double getIlfWeight() {
        return ilfWeight;
    }
}


