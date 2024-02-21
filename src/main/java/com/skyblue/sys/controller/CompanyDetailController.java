package com.skyblue.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skyblue.common.vo.Result;
import com.skyblue.sys.entity.CompanyDetail;
import com.skyblue.sys.entity.StudentDetail;
import com.skyblue.sys.service.ICompanyDetailService;
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
@RequestMapping("/companyDetail")
@CrossOrigin(origins = "http://localhost:8088", allowCredentials = "true")
public class CompanyDetailController {

    @Autowired
    ICompanyDetailService iCompanyDetailService;

    // @ApiOperation("添加企业信息")
    @PostMapping
    public Result<?> addInfo(@RequestBody CompanyDetail companyDetail){
        iCompanyDetailService.save(companyDetail);
        return Result.success("添加企业信息成功");
    }

    //@ApiOperation("更新企业信息")
    @PutMapping
    public Result<?> editInfo(@RequestBody CompanyDetail companyDetail){
        iCompanyDetailService.updateById(companyDetail);
        return Result.success("更新企业信息成功");
    }

    // 获取企业信息
    @GetMapping("/{uid}")
    @ResponseBody
    public Result<?> getInfo(@PathVariable("uid") int uid){
        CompanyDetail companyDetail = iCompanyDetailService.getCompanyDetailByUid(uid);
        if (companyDetail != null) {
            return Result.success(companyDetail);
        } else {
            return Result.fail("未找到企业信息或首次填写信息");
        }
    }

    // 获取匹配的学生信息列表
    @GetMapping("/match/{uid}")
    public Result<?> getMatchedStudents(@PathVariable("uid") int uid,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer size) {
        Page<StudentDetail> studentPage = iCompanyDetailService.getMatchedStudentsByCompanyId(uid, page, size);
        Map<String, Object> data = new HashMap<>();
        data.put("list", studentPage.getRecords());
        data.put("count", studentPage.getTotal());
        return Result.success(data);
    }

}
