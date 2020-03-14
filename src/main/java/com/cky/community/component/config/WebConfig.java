package com.cky.community.component.config;

import com.cky.community.component.intercept.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor myLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将自定义拦截器加到下面，否则在拦截器里使用的Service组件会是Null
        registry.addInterceptor(myLoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/","/index","/login","/register","/bootstrap/**","/css/**",
                        "/fonts/**", "/images/**",  "/img/**", "/js/**", "/article/**");
    }
}
