package com.skyblue.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skyblue.sys.entity.CompanyDetail;
import com.skyblue.sys.entity.StudentDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
// CompanyDetailMapper 接口
public interface CompanyDetailMapper extends BaseMapper<CompanyDetail> {
    // 根据 uid 查询企业详情
    CompanyDetail selectCompanyDetailByUid(Integer uid);

    // 根据 companyId 查询匹配的学生列表（分页）
    List<StudentDetail> selectMatchedStudentsByCompanyId(Page<StudentDetail> page, @Param("companyId") Integer companyId);

    // 查询总剩余岗位配额
    @Select("SELECT SUM(quota) FROM company_detail WHERE position_start > CURDATE() AND quota > 0")
    int sumQuota();

    // 查询完成人数，只包括已经配对的岗位
    @Select("SELECT COUNT(*) FROM company_detail " +
            "WHERE id IN (SELECT company_id FROM job_match) AND position_end < CURDATE()")
    int countFinishedJobs();
}
