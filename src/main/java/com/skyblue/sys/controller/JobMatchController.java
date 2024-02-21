package com.skyblue.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skyblue.common.vo.Result;
import com.skyblue.sys.dto.JobMatchDTO;
import com.skyblue.sys.service.IJobMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
@RestController
@RequestMapping("/jobmatch")
@CrossOrigin(origins = "http://localhost:8088", allowCredentials = "true")
public class JobMatchController {

        @Autowired
        private IJobMatchService jobMatchService;

        @GetMapping("/list")
        public Result<Map<String, Object>> listJobMatches(
                @RequestParam(defaultValue = "1") Integer page,
                @RequestParam(defaultValue = "10") Integer size) {
            Page<JobMatchDTO> jobMatchPage = jobMatchService.listJobMatchesWithDetails(page, size);

            Map<String, Object> data = new HashMap<>();
            data.put("records", jobMatchPage.getRecords());
            data.put("total", jobMatchPage.getTotal());
            data.put("current", jobMatchPage.getCurrent());
            data.put("size", jobMatchPage.getSize());

            return Result.success(data, "获取成功");
        }
    }

