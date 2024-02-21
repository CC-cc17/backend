package com.skyblue.sys.dto;

public class IndustryStatisticsDTO {
    private String industry; // 行业名称
    private Integer studentnum; // 学生数量
    private Integer companynum; // 企业数量

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(Integer studentnum) {
        this.studentnum = studentnum;
    }

    public Integer getCompanynum() {
        return companynum;
    }

    public void setCompanynum(Integer companynum) {
        this.companynum = companynum;
    }
}
