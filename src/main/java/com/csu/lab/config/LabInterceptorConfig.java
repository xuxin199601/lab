package com.csu.lab.config;

//import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import com.csu.bootone.Interceptor.OneInterceptor;

import com.csu.lab.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class LabInterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        这里的addPathPatterns是可以链式调用的。
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/server/*").excludePathPatterns("/server/login");
        super.addInterceptors(registry);
    }
}
