package com.skyblue.sys.service.impl;

import com.skyblue.sys.dto.CountDataDTO;
import com.skyblue.sys.mapper.CompanyDetailMapper;
import com.skyblue.sys.mapper.JobMatchMapper;
import com.skyblue.sys.mapper.StudentDetailMapper;
import com.skyblue.sys.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CountDataService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private StudentDetailMapper studentDetailMapper;

    @Autowired
    private CompanyDetailMapper companyDetailMapper;

    @Autowired
    private JobMatchMapper jobMatchMapper;

    public List<CountDataDTO> getCountData() {
        List<CountDataDTO> data = new ArrayList<>();

        int studentCount = sysUserMapper.selectCountByUserType("student");
        data.add(new CountDataDTO("已登记学生", studentCount, "editPen", "#2ec7c9"));

        int companyCount = sysUserMapper.selectCountByUserType("company");
        data.add(new CountDataDTO("已注册企业", companyCount, "officeBuilding", "#ffb980"));

        int waitingMatchCount = studentDetailMapper.selectWaitingMatchCount();
        data.add(new CountDataDTO("待匹配学生", waitingMatchCount, "more", "#5ab1ef"));

        int totalQuota = companyDetailMapper.sumQuota();
        data.add(new CountDataDTO("待分配岗位", totalQuota, "moreFilled", "#008000"));

        int matchedCount = jobMatchMapper.selectMatchedCount();
        data.add(new CountDataDTO("已配对", matchedCount, "successFilled", "#ff9900"));

        int finishedCount = companyDetailMapper.countFinishedJobs();
        data.add(new CountDataDTO("总完成人数", finishedCount, "finished", "#0000b3"));

        return data;
    }
}