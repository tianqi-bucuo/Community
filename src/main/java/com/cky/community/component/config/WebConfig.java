package com.cky.community.component.config;

import com.cky.community.component.intercept.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/","/index","/login","/register","/bootstrap/**","/css/**","/img/**","/article/**");
    }
}
