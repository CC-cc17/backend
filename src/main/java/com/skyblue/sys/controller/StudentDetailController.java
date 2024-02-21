package com.skyblue.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skyblue.common.vo.Result;
import com.skyblue.sys.entity.CompanyDetail;
import com.skyblue.sys.entity.StudentDetail;
import com.skyblue.sys.service.IStudentDetailService;
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
@RequestMapping("/studentDetail")
@CrossOrigin(origins = "http://localhost:8088", allowCredentials = "true")
public class StudentDetailController {

    @Autowired
    IStudentDetailService iStudentDetailService;

    // @ApiOperation("添加学生信息")
    @PostMapping
    public Result<?> addInfo(@RequestBody StudentDetail studentDetail){
        iStudentDetailService.save(studentDetail);
        return Result.success("添加学生信息成功");
    }

    //@ApiOperation("更新学生信息")
    @PutMapping
    public Result<?> editInfo(@RequestBody StudentDetail studentDetail){
        iStudentDetailService.updateById(studentDetail);
        return Result.success("更新学生信息成功");
    }

    // 获取学生信息
    @GetMapping("/{uid}")
    @ResponseBody
    public Result<?> getInfo(@PathVariable("uid") int uid){
        StudentDetail studentDetail = iStudentDetailService.getStudentDetailByUid(uid);
        if (studentDetail != null) {
            return Result.success(studentDetail);
        } else {
            return Result.fail("未找到学生信息或首次填写信息");
        }
    }

    // 获取匹配的企业信息列表
    @GetMapping("/match/{uid}")
    public Result<?> getMatchedCompanies(@PathVariable("uid") int uid,
                                         @RequestParam(defaultValue = "1") Integer page,
                                         @RequestParam(defaultValue = "10") Integer size) {
        Page<CompanyDetail> companyPage = iStudentDetailService.getMatchedCompaniesByStudentId(uid, page, size);
        Map<String, Object> data = new HashMap<>();
        data.put("list", companyPage.getRecords());
        data.put("count", companyPage.getTotal());
        return Result.success(data);
    }

}
