package com.skyblue.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.skyblue.sys.dto.BarDataDTO;
import com.skyblue.sys.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gd
 * @since 2024-02-15
 */
public interface ISysUserService extends IService<SysUser> {

    Map<String, Object> login(SysUser sysUser);

    Map<String, Object> getUserInfo(String token);

    void logout(String token);

    Page<SysUser> getUserData(Integer page, Integer size, String name, String role);

    List<BarDataDTO> getWeeklyUserStatistics();
}
