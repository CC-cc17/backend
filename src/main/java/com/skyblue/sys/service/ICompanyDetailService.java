package com.skyblue.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skyblue.sys.entity.CompanyDetail;
import com.skyblue.sys.entity.StudentDetail;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
public interface ICompanyDetailService extends IService<CompanyDetail> {

    CompanyDetail getCompanyDetailByUid(Integer uid);

    Page<StudentDetail> getMatchedStudentsByCompanyId(int uid, Integer page, Integer size);
}

