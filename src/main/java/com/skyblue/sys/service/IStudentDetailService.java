package com.skyblue.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skyblue.sys.entity.CompanyDetail;
import com.skyblue.sys.entity.StudentDetail;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
public interface IStudentDetailService extends IService<StudentDetail> {

    StudentDetail getStudentDetailByUid(Integer uid);

    Page<CompanyDetail> getMatchedCompaniesByStudentId(int uid, Integer page, Integer size);
}
