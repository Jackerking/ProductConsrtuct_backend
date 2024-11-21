package com.rjgj.zjpg.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Project {
    private int projectId; // 主键
    private String projectName;
    private int stdId;
    private float AE;
    private float RSK;
    private int id;
    private double unadjustedFunctionPoints;
    private double adjustedFunctionPoints;
    private int personnelCosts;
    private String filePath;
    private double totalCost;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date projectTime; // 使用 Date 类型存储时间
    private int userId;
    private int EO;
    private int EI;
    private int EQ;
    private int ILF;
    private int EIF;

    public Project() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getStdId() {
        return stdId;
    }

    public void setStdId(int stdId) {
        this.stdId = stdId;
    }

    public float getAE() {
        return AE;
    }

    public void setAE(float AE) {
        this.AE = AE;
    }

    public float getRSK() {
        return RSK;
    }

    public void setRSK(float RSK) {
        this.RSK = RSK;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUnadjustedFunctionPoints() {
        return unadjustedFunctionPoints;
    }

    public void setUnadjustedFunctionPoints(double unadjustedFunctionPoints) {
        this.unadjustedFunctionPoints = unadjustedFunctionPoints;
    }

    public double getAdjustedFunctionPoints() {
        return adjustedFunctionPoints;
    }

    public void setAdjustedFunctionPoints(double adjustedFunctionPoints) {
        this.adjustedFunctionPoints = adjustedFunctionPoints;
    }

    public int getPersonnelCosts() {
        return personnelCosts;
    }

    public void setPersonnelCosts(int personnelCosts) {
        this.personnelCosts = personnelCosts;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Date getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(Date projectTime) {
        this.projectTime = projectTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEO() {
        return EO;
    }

    public void setEO(int EO) {
        this.EO = EO;
    }

    public int getEI() {
        return EI;
    }

    public void setEI(int EI) {
        this.EI = EI;
    }

    public int getEQ() {
        return EQ;
    }

    public void setEQ(int EQ) {
        this.EQ = EQ;
    }

    public int getILF() {
        return ILF;
    }

    public void setILF(int ILF) {
        this.ILF = ILF;
    }

    public int getEIF() {
        return EIF;
    }

    public void setEIF(int EIF) {
        this.EIF = EIF;
    }
}
