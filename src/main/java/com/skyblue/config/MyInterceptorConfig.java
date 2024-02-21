package com.skyblue.config;

import com.skyblue.interceptor.JwtValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private JwtValidateInterceptor jwtValidateInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtValidateInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/user/login",
                        "/user/register",
                        "/user/info",
                        "/user/logout",
                        "/error",
                        "/swagger-ui/**", // 保留这个以兼容旧版本
                        "/swagger-ui.html", // 添加这个以兼容新的Springdoc访问路径
                        "/swagger-ui/index.html", // 也可能需要添加这个路径
                        "/swagger-resources/**",
                        "/v3/api-docs/**", // 确保API文档的路径也被排除
                        "/webjars/**",
                        "/favicon.ico",//Swagger UI的静态资源也需要被排除
                        "/data/**"// 获取表单数据不用验证
                );
    }
}