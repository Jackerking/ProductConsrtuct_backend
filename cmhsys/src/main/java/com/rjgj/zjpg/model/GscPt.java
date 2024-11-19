package com.rjgj.zjpg.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GscPt {
    @JsonProperty("communication")
    private Integer communication;
    @JsonProperty("onlineUpgrade")
    private Integer onlineUpgrade;
    @JsonProperty("distributedProcessing")
    private Integer distributedProcessing;
    @JsonProperty("complexity")
    private Integer complexity;
    @JsonProperty("performance")
    private Integer performance;
    @JsonProperty("reusability")
    private Integer reusability;
    @JsonProperty("configuration")
    private Integer configuration;
    @JsonProperty("easeOfInstallation")
    private Integer easeOfInstallation;
    @JsonProperty("transactionFrequency")
    private Integer transactionFrequency;
    @JsonProperty("easeOfOperation")
    private Integer easeOfOperation;
    @JsonProperty("onlineDataEntry")
    private Integer onlineDataEntry;
    @JsonProperty("crossPlatform")
    private Integer crossPlatform;
    @JsonProperty("EndUserUsageRate")
    private Integer EndUserUsageRate;
    @JsonProperty("flexibility")
    private Integer flexibility;

    public Integer getCommunication() {
        return communication;
    }

    public void setCommunication(Integer communication) {
        this.communication = communication;
    }

    public Integer getOnlineUpgrade() {
        return onlineUpgrade;
    }

    public void setOnlineUpgrade(Integer onlineUpgrade) {
        this.onlineUpgrade = onlineUpgrade;
    }

    public Integer getDistributedProcessing() {
        return distributedProcessing;
    }

    public void setDistributedProcessing(Integer distributedProcessing) {
        this.distributedProcessing = distributedProcessing;
    }

    public Integer getComplexity() {
        return complexity;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Integer getReusability() {
        return reusability;
    }

    public void setReusability(Integer reusability) {
        this.reusability = reusability;
    }

    public Integer getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Integer configuration) {
        this.configuration = configuration;
    }

    public Integer getEaseOfInstallation() {
        return easeOfInstallation;
    }

    public void setEaseOfInstallation(Integer easeOfInstallation) {
        this.easeOfInstallation = easeOfInstallation;
    }

    public Integer getTransactionFrequency() {
        return transactionFrequency;
    }

    public void setTransactionFrequency(Integer transactionFrequency) {
        this.transactionFrequency = transactionFrequency;
    }

    public Integer getEaseOfOperation() {
        return easeOfOperation;
    }

    public void setEaseOfOperation(Integer easeOfOperation) {
        this.easeOfOperation = easeOfOperation;
    }

    public Integer getOnlineDataEntry() {
        return onlineDataEntry;
    }

    public void setOnlineDataEntry(Integer onlineDataEntry) {
        this.onlineDataEntry = onlineDataEntry;
    }

    public Integer getCrossPlatform() {
        return crossPlatform;
    }

    public void setCrossPlatform(Integer crossPlatform) {
        this.crossPlatform = crossPlatform;
    }

    public Integer getEndUserUsageRate() {
        return EndUserUsageRate;
    }

    public void setEndUserUsageRate(Integer endUserUsageRate) {
        EndUserUsageRate = endUserUsageRate;
    }

    public Integer getFlexibility() {
        return flexibility;
    }

    public void setFlexibility(Integer flexibility) {
        this.flexibility = flexibility;
    }
}
