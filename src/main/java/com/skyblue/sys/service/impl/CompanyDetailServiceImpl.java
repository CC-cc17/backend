package com.skyblue.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skyblue.sys.entity.CompanyDetail;
import com.skyblue.sys.entity.StudentDetail;
import com.skyblue.sys.mapper.CompanyDetailMapper;
import com.skyblue.sys.service.ICompanyDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
@Service
public class CompanyDetailServiceImpl extends ServiceImpl<CompanyDetailMapper, CompanyDetail> implements ICompanyDetailService {


    @Autowired
    private CompanyDetailMapper companyDetailMapper;
    @Override
    public CompanyDetail getCompanyDetailByUid(Integer uid) {
        return companyDetailMapper.selectCompanyDetailByUid(uid);
    }

    @Override
    public Page<StudentDetail> getMatchedStudentsByCompanyId(int uid, Integer page, Integer size) {
        Page<StudentDetail> studentPage = new Page<>(page, size);
        CompanyDetail companyDetail = getCompanyDetailByUid(uid);
        if (companyDetail != null) {
            List<StudentDetail> students = companyDetailMapper.selectMatchedStudentsByCompanyId(studentPage, companyDetail.getId());
            studentPage.setRecords(students);
        }
        return studentPage;
    }
}
