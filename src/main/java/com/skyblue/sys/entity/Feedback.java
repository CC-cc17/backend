package com.skyblue.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
@TableName("feedback")
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 反馈id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 配对表id
     */
    private Integer jobMatchId;

    /**
     * 学生评价
     */
    private String studentComment;

    /**
     * 学生打分
     */
    private String studentRating;

    /**
     * 学生反馈时间
     */
    private LocalDateTime studentFeedbackTime;

    /**
     * 企业评价
     */
    private String companyComment;

    /**
     * 企业打分
     */
    private String companyRating;

    /**
     * 企业反馈时间
     */
    private LocalDateTime companyFeedbackTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobMatchId() {
        return jobMatchId;
    }

    public void setJobMatchId(Integer jobMatchId) {
        this.jobMatchId = jobMatchId;
    }

    public String getStudentComment() {
        return studentComment;
    }

    public void setStudentComment(String studentComment) {
        this.studentComment = studentComment;
    }

    public String getStudentRating() {
        return studentRating;
    }

    public void setStudentRating(String studentRating) {
        this.studentRating = studentRating;
    }

    public LocalDateTime getStudentFeedbackTime() {
        return studentFeedbackTime;
    }

    public void setStudentFeedbackTime(LocalDateTime studentFeedbackTime) {
        this.studentFeedbackTime = studentFeedbackTime;
    }

    public String getCompanyComment() {
        return companyComment;
    }

    public void setCompanyComment(String companyComment) {
        this.companyComment = companyComment;
    }

    public String getCompanyRating() {
        return companyRating;
    }

    public void setCompanyRating(String companyRating) {
        this.companyRating = companyRating;
    }

    public LocalDateTime getCompanyFeedbackTime() {
        return companyFeedbackTime;
    }

    public void setCompanyFeedbackTime(LocalDateTime companyFeedbackTime) {
        this.companyFeedbackTime = companyFeedbackTime;
    }

    @Override
    public String toString() {
        return "Feedback{" +
            "id = " + id +
            ", jobMatchId = " + jobMatchId +
            ", studentComment = " + studentComment +
            ", studentRating = " + studentRating +
            ", studentFeedbackTime = " + studentFeedbackTime +
            ", companyComment = " + companyComment +
            ", companyRating = " + companyRating +
            ", companyFeedbackTime = " + companyFeedbackTime +
        "}";
    }
}
