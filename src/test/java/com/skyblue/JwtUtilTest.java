package com.skyblue;


import com.skyblue.common.utils.JwtUtil;
import com.skyblue.sys.entity.SysUser;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {
    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void testCreateJwt(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("peterzhang");
        sysUser.setPhone("666");
        String token = jwtUtil.createToken(sysUser);
        System.out.println(token);
    }

    @Test
    public void testPhaseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NDRiMGNhMy1kNjM3LTRiNzktOWNhYS0xZjBjYzMxZGVjOGEiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiNjY2XCIsXCJ1c2VybmFtZVwiOlwicGV0ZXJ6aGFuZ1wifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTcwODMxMDcyNSwiZXhwIjoxNzA4MzEyNTI1fQ.HCbZuas6_EsBgg3qm8rSid24pWrTfC8BKZl-yz0Barc";
        Claims claims = jwtUtil.parseToken(token);
        System.out.println(claims);
    }

    @Test
    public void testPhaseJwt2(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI1NDRiMGNhMy1kNjM3LTRiNzktOWNhYS0xZjBjYzMxZGVjOGEiLCJzdWIiOiJ7XCJwaG9uZVwiOlwiNjY2XCIsXCJ1c2VybmFtZVwiOlwicGV0ZXJ6aGFuZ1wifSIsImlzcyI6InN5c3RlbSIsImlhdCI6MTcwODMxMDcyNSwiZXhwIjoxNzA4MzEyNTI1fQ.HCbZuas6_EsBgg3qm8rSid24pWrTfC8BKZl-yz0Barc";
        SysUser sysUser = jwtUtil.parseToken(token,SysUser.class);
        System.out.println(sysUser);
    }

}
