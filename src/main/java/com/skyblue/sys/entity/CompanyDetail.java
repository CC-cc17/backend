package com.skyblue.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("company_detail")
public class CompanyDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 企业账户id
     */
    private Integer uid;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 行业类型
     */
    private Integer industryType;

    /**
     * 性别需求
     */
    private String genderRequire;

    /**
     * 开始时间
     */
    private LocalDate positionStart;

    /**
     * 结束时间
     */
    private LocalDate positionEnd;

    /**
     * 名额
     */
    private Integer quota;

    /**
     * 职位介绍
     */
    private String positionDescribe;

    /**
     * 联系人
     */
    private String contactPerson;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 企业地址
     */
    private String companyAddress;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getIndustryType() {
        return industryType;
    }

    public void setIndustryType(Integer industryType) {
        this.industryType = industryType;
    }

    public String getGenderRequire() {
        return genderRequire;
    }

    public void setGenderRequire(String genderRequire) {
        this.genderRequire = genderRequire;
    }

    public LocalDate getPositionStart() {
        return positionStart;
    }

    public void setPositionStart(LocalDate positionStart) {
        this.positionStart = positionStart;
    }

    public LocalDate getPositionEnd() {
        return positionEnd;
    }

    public void setPositionEnd(LocalDate positionEnd) {
        this.positionEnd = positionEnd;
    }

    public Integer getQuota() {
        return quota;
    }

    public void setQuota(Integer quota) {
        this.quota = quota;
    }

    public String getPositionDescribe() {
        return positionDescribe;
    }

    public void setPositionDescribe(String positionDescribe) {
        this.positionDescribe = positionDescribe;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    @Override
    public String toString() {
        return "CompanyDetail{" +
            "id = " + id +
            ", uid = " + uid +
            ", companyName = " + companyName +
            ", industryType = " + industryType +
            ", genderRequire = " + genderRequire +
            ", positionStart = " + positionStart +
            ", positionEnd = " + positionEnd +
            ", quota = " + quota +
            ", positionDescribe = " + positionDescribe +
            ", contactPerson = " + contactPerson +
            ", contactPhone = " + contactPhone +
            ", companyAddress = " + companyAddress +
        "}";
    }
}
