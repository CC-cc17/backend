package com.skyblue.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.skyblue.common.utils.JwtUtil;
import com.skyblue.sys.dto.BarDataDTO;
import com.skyblue.sys.entity.SysUser;
import com.skyblue.sys.mapper.SysUserMapper;
import com.skyblue.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gd
 * @since 2024-02-15
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public Map<String, Object> login(SysUser sysUser) {
        //根据用户名和密码做一个查询
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername,sysUser.getUsername());
        //wrapper.eq(SysUser::getPassword,sysUser.getPassword());
        SysUser loginUser = this.baseMapper.selectOne(wrapper);
        //结果不为空，生成一个token，并且用户信息放到redis
        if(loginUser != null && passwordEncoder.matches(sysUser.getPassword(),loginUser.getPassword())){

            // 暂时用UUID，TODO改成JWT
            //String key = "user:" + UUID.randomUUID();

            //存入redis
            loginUser.setPassword(null);
            // redisTemplate.opsForValue().set(key,loginUser,30, TimeUnit.MINUTES);


            /*创建JWT*/
            String token = jwtUtil.createToken(loginUser);

            //返回数据
            Map<String, Object> data = new HashMap<>();
            data.put("token",token);
            return data;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        //根据token获取用户信息,redis
        // Object obj = redisTemplate.opsForValue().get(token);
        //反序列化
        SysUser loginUser = null;
        try {
             loginUser = jwtUtil.parseToken(token,SysUser.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(loginUser != null){
            //redis 反序列化处理
            // SysUser loginUser = JSON.parseObject(JSON.toJSONString(obj),SysUser.class);
            Map<String, Object> data = new HashMap<>();
            data.put("uid",loginUser.getUid());
            data.put("username",loginUser.getUsername());
            data.put("role",loginUser.getUserType());
            data.put("email",loginUser.getEmail());
            data.put("phone",loginUser.getPhone());
            data.put("password",loginUser.getPassword());
            data.put("createTime",loginUser.getCreateTime());
            return data;
        }
        return null;
    }

    @Override
    public void logout(String token) {
        //注销redis
        //redisTemplate.delete(token);
    }

    @Override
    public  Page<SysUser> getUserData(Integer page, Integer size, String name, String userType) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        if (name != null && !name.trim().isEmpty()) {
            queryWrapper.like("username", name);
        }
        if (userType != null && !userType.trim().isEmpty()) {
            queryWrapper.eq("user_type", userType);
        }
        return this.page(new Page<>(page, size), queryWrapper);
    }


    @Override
    public List<BarDataDTO> getWeeklyUserStatistics() {
        LocalDate today = LocalDate.now();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        LocalDate startOfWeek = today.minusDays(dayOfWeek.getValue() - 1);
        List<BarDataDTO> statistics = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            LocalDate date = startOfWeek.plusDays(i);
            String dayName = translateToChineseDayOfWeek(date.getDayOfWeek());
            int newUserCount = 0;
            if (!date.isAfter(today)) {
                // 只有当日期不在未来时才查询数据库
                newUserCount = baseMapper.getNewUserCountByDate(date); // 使用baseMapper调用方法
            }
            statistics.add(new BarDataDTO(dayName, newUserCount));
        }
        return statistics;
    }

    private String translateToChineseDayOfWeek(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:    return "周一";
            case TUESDAY:   return "周二";
            case WEDNESDAY: return "周三";
            case THURSDAY:  return "周四";
            case FRIDAY:    return "周五";
            case SATURDAY:  return "周六";
            case SUNDAY:    return "周日";
            default:        throw new IllegalArgumentException("Invalid day of the week: " + dayOfWeek);
        }
    }
}
