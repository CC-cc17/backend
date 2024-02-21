package com.skyblue.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>
 * 
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
@TableName("student_detail")
public class StudentDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生账户id
     */
    private Integer uid;

    /**
     * 学生姓名
     */
    private String name;

    /**
     * 行业类型
     */
    private Integer industryType;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String gender;

    /**
     * 可开始时间
     */
    private LocalDate availableStart;

    /**
     * 可结束时间
     */
    private LocalDate availableEnd;

    /**
     * 配对状态
     */
    private Boolean matchStatus;

    /**
     * 自我介绍
     */
    @TableField("`describe`")
    private String describe;

    /**
     * 监护人姓名
     */
    private String supervisor;

    /**
     * 监护人联系电话
     */
    private String supervisorPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIndustryType() {
        return industryType;
    }

    public void setIndustryType(Integer industryType) {
        this.industryType = industryType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getAvailableStart() {
        return availableStart;
    }

    public void setAvailableStart(LocalDate availableStart) {
        this.availableStart = availableStart;
    }

    public LocalDate getAvailableEnd() {
        return availableEnd;
    }

    public void setAvailableEnd(LocalDate availableEnd) {
        this.availableEnd = availableEnd;
    }

    public Boolean getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(Boolean matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone(String supervisorPhone) {
        this.supervisorPhone = supervisorPhone;
    }

    @Override
    public String toString() {
        return "StudentDetail{" +
            "id = " + id +
            ", uid = " + uid +
            ", name = " + name +
            ", industryType = " + industryType +
            ", age = " + age +
            ", gender = " + gender +
            ", availableStart = " + availableStart +
            ", availableEnd = " + availableEnd +
            ", matchStatus = " + matchStatus +
            ", describe = " + describe +
            ", supervisor = " + supervisor +
            ", supervisorPhone = " + supervisorPhone +
        "}";
    }
}
