package com.roy.bean;

import java.io.Serializable;

/**
 * description： 部门实体
 * author：dingyawu
 * date：created in 14:43 2020/8/29
 * history:
 */
public class Dept implements Serializable {

    private static final long serialVersionUID = 3713090895418195361L;
    private Integer id;
    private String name;
    public Dept() {
    }

    public Dept(Integer id, String name) {
        this.id = id;
        this.name = name;
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
        return "Dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
