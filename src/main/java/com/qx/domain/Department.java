package com.qx.domain;

public class Department {
    private String id;//部门编号
    private String name;//部门名称
    //提供对应的setter/getter方法
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Department [id=" + id + ", name=" + name + "]";
    }


}
