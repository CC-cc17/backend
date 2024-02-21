package com.skyblue.common.utils;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    // 有效期
    private static final long JWT_EXPIRE = 60*60*1000L;  //半小时
    // 令牌秘钥
    private static final String JWT_KEY = "123456";

    public  String createToken(Object data){
        // 当前时间
        long currentTime = System.currentTimeMillis();
        // 过期时间
        long expTime = currentTime+JWT_EXPIRE;
        // 构建jwt
        JwtBuilder builder = Jwts.builder()
                .setId(UUID.randomUUID()+"")
                .setSubject(JSON.toJSONString(data))
                .setIssuer("system")
                .setIssuedAt(new Date(currentTime))
                .signWith(SignatureAlgorithm.HS256, encodeSecret(JWT_KEY))
                .setExpiration(new Date(expTime));
        return builder.compact();
    }

    private SecretKey encodeSecret(String key){
        // HS256算法需要的密钥至少应该是256位（32字节）
        byte[] keyBytes = key.getBytes();
        keyBytes = Arrays.copyOf(keyBytes, 32); // 确保密钥长度至少为32字节
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims parseToken(String token){
        Claims body = Jwts.parserBuilder()
                .setSigningKey(encodeSecret(JWT_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return body;
    }

    public <T> T parseToken(String token,Class<T> clazz){
        Claims body = Jwts.parserBuilder()
                .setSigningKey(encodeSecret(JWT_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return JSON.parseObject(body.getSubject(),clazz);
    }

}
