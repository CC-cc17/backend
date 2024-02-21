package com.skyblue.sys.controller;

import com.skyblue.common.vo.Result;
import com.skyblue.sys.dto.BarDataDTO;
import com.skyblue.sys.dto.CountDataDTO;
import com.skyblue.sys.dto.IndustryStatisticsDTO;
import com.skyblue.sys.dto.PieDataDTO;
import com.skyblue.sys.service.ISysUserService;
import com.skyblue.sys.service.impl.CountDataService;
import com.skyblue.sys.service.impl.IndustryStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/data")
@CrossOrigin(origins = "http://localhost:8088", allowCredentials = "true")
public class DataController {

    @Autowired
    private IndustryStatisticsService industryStatisticsService;

    @Autowired
    private CountDataService countDataService;

    @Autowired
    private ISysUserService iSysUserService;


    //行业人数表格数据
    @GetMapping("/tableData")
    public Result<?> getIndustryStatistics() {
        List<IndustryStatisticsDTO> statistics = industryStatisticsService.getIndustryStatistics();
        return Result.success(statistics,"查询成功");
    }

    //获取饼状图数据
    @GetMapping("/pieData")
    public Result<?> getPieData() {
        List<PieDataDTO> data = industryStatisticsService.getPieData();
        return Result.success(data,"饼状图数据获取成功");
    }

    //获得柱状图数据
    @GetMapping("/barData")
    public Result<?> getWeeklyUserStatistics() {
        List<BarDataDTO> data = iSysUserService.getWeeklyUserStatistics();
        return Result.success(data,"柱状图数据获取成功");
    }

    //获取

    // 统计表格数据
    @GetMapping("/countData")
    public Result<?> getCountData() {
        try {
            List<CountDataDTO> countData = countDataService.getCountData();
            return Result.success(countData, "统计数据查询成功");
        } catch (Exception e) {
            return Result.fail("统计数据查询失败");
        }
    }
}

