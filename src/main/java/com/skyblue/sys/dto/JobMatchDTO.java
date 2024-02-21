package com.skyblue.sys.dto;

import com.skyblue.sys.entity.CompanyDetail;
import com.skyblue.sys.entity.StudentDetail;

import java.time.LocalDateTime;

public class JobMatchDTO {
    private Integer id;
    private LocalDateTime matchTime;
    private StudentDetail student;
    private CompanyDetail company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(LocalDateTime matchTime) {
        this.matchTime = matchTime;
    }

    public StudentDetail getStudent() {
        return student;
    }

    public void setStudent(StudentDetail student) {
        this.student = student;
    }

    public CompanyDetail getCompany() {
        return company;
    }

    public void setCompany(CompanyDetail company) {
        this.company = company;
    }
}

