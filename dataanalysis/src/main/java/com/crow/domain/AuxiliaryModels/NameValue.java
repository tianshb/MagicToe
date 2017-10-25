package com.crow.domain.AuxiliaryModels;

/**
 * Created by CrowHawk on 17/10/23.
 */

/**
 * 用来拼接json数据返回给前端
 */
public class NameValue {
    private Integer value;
    private String name;

    public NameValue(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
