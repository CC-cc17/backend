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
@TableName("job_match")
public class JobMatch implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 配对id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 配对的学生id
     */
    private Integer studentId;

    /**
     * 配对的企业id
     */
    private Integer companyId;

    /**
     * 配对时间
     */
    private LocalDateTime matchTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public LocalDateTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalDateTime matchTime) {
        this.matchTime = matchTime;
    }

    @Override
    public String toString() {
        return "JobMatch{" +
            "id = " + id +
            ", studentId = " + studentId +
            ", companyId = " + companyId +
            ", matchTime = " + matchTime +
        "}";
    }
}
