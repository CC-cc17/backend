package com.skyblue.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skyblue.sys.entity.CompanyDetail;
import com.skyblue.sys.entity.StudentDetail;
import com.skyblue.sys.mapper.StudentDetailMapper;
import com.skyblue.sys.service.IStudentDetailService;
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
public class StudentDetailServiceImpl extends ServiceImpl<StudentDetailMapper, StudentDetail> implements IStudentDetailService {

    @Autowired
    StudentDetailMapper studentDetailMapper;
    @Override
    public StudentDetail getStudentDetailByUid(Integer uid) {
        return studentDetailMapper.selectStudentDetailByUid(uid);
    }

    @Override
    public Page<CompanyDetail> getMatchedCompaniesByStudentId(int uid, Integer page, Integer size) {
        Page<CompanyDetail> companyPage = new Page<>(page, size);
        StudentDetail studentDetail = getStudentDetailByUid(uid);
        if (studentDetail != null) {
            List<CompanyDetail> companies = studentDetailMapper.selectMatchedCompaniesByStudentId(companyPage, studentDetail.getId());
            companyPage.setRecords(companies);
        }
        return companyPage;
    }
}
