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
public interface StudentDetailMapper extends BaseMapper<StudentDetail> {

    StudentDetail selectStudentDetailByUid(Integer uid);

    // 根据 studentId 查询匹配的企业列表（分页）
    List<CompanyDetail> selectMatchedCompaniesByStudentId(Page<CompanyDetail> page, @Param("studentId") Integer studentId);

    // 查询待匹配学生数量
    @Select("SELECT COUNT(*) FROM student_detail WHERE match_status = 0")
    int selectWaitingMatchCount();

}
