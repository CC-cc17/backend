package com.skyblue.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skyblue.sys.dto.IndustryStatisticsDTO;
import com.skyblue.sys.dto.PieDataDTO;
import com.skyblue.sys.entity.Industry;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IndustryStatisticsMapper extends BaseMapper<Industry> {
    @Select("SELECT ind.name as industry, " +
            "(SELECT COUNT(*) FROM student_detail WHERE industry_type = ind.id) as studentnum, " +
            "(SELECT COUNT(*) FROM company_detail WHERE industry_type = ind.id) as companynum " +
            "FROM industry ind " +
            "ORDER BY studentnum DESC, companynum DESC")
    List<IndustryStatisticsDTO> getIndustryStatistics();

    @Select("SELECT ind.name as name, " +
            "((SELECT COUNT(*) FROM student_detail WHERE industry_type = ind.id) + " +
            "(SELECT COUNT(*) FROM company_detail WHERE industry_type = ind.id)) as value " +
            "FROM industry ind " +
            "ORDER BY value DESC " +
            "LIMIT 7")
    List<PieDataDTO> getPieData();
}