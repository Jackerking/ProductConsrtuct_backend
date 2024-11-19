package com.rjgj.zjpg.entity;

import java.util.ArrayList;
import java.util.List;

public class Province {
    private String name;  // 映射 city 字段
    private List<Integer> years;

    // Constructor
    public Province(String name, List<Integer> years) {
        this.name = name;
        this.years = years;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }
}
