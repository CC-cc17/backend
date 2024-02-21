package com.skyblue.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skyblue.sys.dto.JobMatchDTO;
import com.skyblue.sys.entity.JobMatch;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
public interface IJobMatchService extends IService<JobMatch> {

    Page<JobMatchDTO> listJobMatchesWithDetails(Integer page, Integer size);
}
