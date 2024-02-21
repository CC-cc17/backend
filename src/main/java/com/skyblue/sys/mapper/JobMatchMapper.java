package com.skyblue.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skyblue.sys.dto.JobMatchDTO;
import com.skyblue.sys.entity.JobMatch;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
public interface JobMatchMapper extends BaseMapper<JobMatch> {

    Page<JobMatchDTO> getJobMatchDetails(Page<JobMatchDTO> dtoPage);

    // 查询已配对数量
    @Select("SELECT COUNT(*) FROM job_match")
    int selectMatchedCount();
}
