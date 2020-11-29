package com.roy.bean;

import java.util.List;

/**
 * description： 部门实体
 * author：dingyawu
 * date：created in 14:43 2020/8/29
 * history:
 */
public class DeptPlus {
    private Integer id;
    private String name;
    private List<Employee> emps;

    public DeptPlus() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
        return "DeptPlus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emps=" + emps +
                '}';
    }
}
