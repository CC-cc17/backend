package com.skyblue.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skyblue.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gd
 * @since 2024-02-18
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    // 查询不同用户类型的数量
    @Select("SELECT COUNT(*) FROM sys_user WHERE user_type = #{userType}")
    int selectCountByUserType(@Param("userType") String userType);

    @Select("SELECT COUNT(*) FROM sys_user WHERE DATE(create_time) = #{date}")
    Integer getNewUserCountByDate(@Param("date") LocalDate date);
}
