package com.skyblue.sys.service.impl;

import com.skyblue.sys.dto.IndustryStatisticsDTO;
import com.skyblue.sys.dto.PieDataDTO;
import com.skyblue.sys.mapper.IndustryStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndustryStatisticsService {

    @Autowired
    private IndustryStatisticsMapper industryStatisticsMapper;

    public List<IndustryStatisticsDTO> getIndustryStatistics() {
        return industryStatisticsMapper.getIndustryStatistics();
    }

    public List<PieDataDTO> getPieData() {
        return industryStatisticsMapper.getPieData();
    }
}