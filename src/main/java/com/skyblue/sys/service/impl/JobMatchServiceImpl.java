package com.skyblue.sys.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skyblue.sys.dto.JobMatchDTO;
import com.skyblue.sys.entity.JobMatch;
import com.skyblue.sys.mapper.JobMatchMapper;
import com.skyblue.sys.service.IJobMatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
@Service
public class JobMatchServiceImpl extends ServiceImpl<JobMatchMapper, JobMatch> implements IJobMatchService {

    @Override
    public Page<JobMatchDTO> listJobMatchesWithDetails(Integer page, Integer size) {
        Page<JobMatchDTO> dtoPage = new Page<>(page, size);
        return baseMapper.getJobMatchDetails(dtoPage);
    }
}
