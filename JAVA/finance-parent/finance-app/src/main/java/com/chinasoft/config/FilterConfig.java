package com.chinasoft.config;

import com.chinasoft.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Auther: 汪毅
 * @Date: 2018/6/21 17:55
 * @Description: 增加自定义过滤器
 */
@Configuration
public class FilterConfig {

    /**
     * 日志打印
     * @return
     */
    @Bean
    @Order(Integer.MIN_VALUE)
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LogFilter());
        registration.addUrlPatterns("/*");
        registration.setName("logFilter");
        return registration;
    }

    /**
     * 跨域支持
     * @return
     */
    @Bean
    @Order(Integer.MIN_VALUE + 1)
    public FilterRegistrationBean corsFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        registration.setFilter(new CorsFilter(source));
        registration.addUrlPatterns("/*");
        registration.setName("corsFilter");
        return registration;
    }


}
