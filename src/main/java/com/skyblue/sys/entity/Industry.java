package com.skyblue.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */@TableName("industry")
public class Industry implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 行业id
     */
    private Integer id;

    /**
     * 行业名称
     */
    private String name;

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
        return "Industry{" +
            "id = " + id +
            ", name = " + name +
        "}";
    }
}
