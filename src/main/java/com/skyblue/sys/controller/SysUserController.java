package com.skyblue.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.skyblue.common.vo.Result;
import com.skyblue.sys.entity.SysUser;
import com.skyblue.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gd
 * @since 2024-02-15
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:8088", allowCredentials = "true")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //@ApiOperation("获取所有用户表单")
    @GetMapping("/all")
    public Result<List<SysUser>> getAllUser() {
        List<SysUser> list = iSysUserService.list();
        return Result.success(list,"查询成功");
    }

    //@ApiOperation("登录请求")
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody SysUser sysUser){
        Map<String,Object> data = iSysUserService.login(sysUser);
        if(data !=null){
            return Result.success(data);
        }
        return  Result.fail(202,"用户名或密码错误");
    }

    //@ApiOperation("获取登录用户信息")
    @GetMapping("/info")
    public Result<Map<String,Object>> getUserInfo(@RequestParam("token") String token){
        //根据token获取用户信息,redis
        Map<String,Object> data = iSysUserService.getUserInfo(token);
        if(data !=null){
            return Result.success(data);
        }
        return  Result.fail(203,"登录信息无效，请重新登录");
    }

   //@ApiOperation("用户登出")
    @PostMapping("/logout")
    public Result<?> logout(@CookieValue(name = "token", required = false) String token) {
        if (token == null || token.isEmpty()) {
            // 处理token为空的情况，比如返回特定的错误信息
            return Result.fail("无法获取token");
        }
        iSysUserService.logout(token);
        return Result.success();
    }

    //@ApiOperation("获取用户表单(分页查询版)")
    @GetMapping("/list")
    public Result<?> getUserData(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String role) {
        Page<SysUser> userPage = iSysUserService.getUserData(page, size, name, role);

        Map<String,Object> data = new HashMap<>();
        data.put("list",userPage.getRecords());
        data.put("count",userPage.getTotal());


        return Result.success(data,"获取成功");
    }

   // @ApiOperation("新增用户")
    @PostMapping
    public Result<?> addUser(@RequestBody SysUser sysUser){
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        iSysUserService.save(sysUser);
        return Result.success("新增用户成功");
    }

    //@ApiOperation("用户注册")
    @PostMapping("/register")
    public Result<?> register(@RequestBody SysUser sysUser){
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        iSysUserService.save(sysUser);
        return Result.success("用户注册成功");
    }

    //@ApiOperation("更新用户信息(管理员)")
    @PutMapping
    public Result<?> editUser(@RequestBody SysUser sysUser){
        sysUser.setPassword(null);
        iSysUserService.updateById(sysUser);
        return Result.success("修改用户成功");
    }

    //@ApiOperation("更新用户信息(登录用户)")
    @PutMapping("/updateInfo")
    public Result<?> updateUserInfo(@RequestBody SysUser sysUser){
        // 从数据库中获取当前的用户信息
        SysUser currentUser = iSysUserService.getById(sysUser);
        if (currentUser == null) {
            return Result.fail("用户不存在");
        }
        // 更新用户信息，但是只有当密码非空且不为空字符串时才更新密码
        if (sysUser.getPassword() != null && !sysUser.getPassword().isEmpty()) {
            currentUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        }
        // 更新其他字段，例如用户名、电话和电子邮件
        currentUser.setUsername(sysUser.getUsername());
        currentUser.setPhone(sysUser.getPhone());
        currentUser.setEmail(sysUser.getEmail());
        // 将更新后的用户信息保存到数据库
        iSysUserService.updateById(currentUser);
        return Result.success("更新用户信息成功");
    }


   // @ApiOperation("根据用户uid获取用户信息")
    @GetMapping("/{id}")
    public Result<SysUser> getUserById(@PathVariable("id") Integer id){
        SysUser sysUser = iSysUserService.getById(id);
        return Result.success(sysUser);
    }


    //@ApiOperation("删除用户(物理删除)")
    @DeleteMapping("/{id}")
    public Result<SysUser> deleteUserById(@PathVariable("id") Integer id){
        iSysUserService.removeById(id);
        return Result.success("删除用户成功");
    }


}




