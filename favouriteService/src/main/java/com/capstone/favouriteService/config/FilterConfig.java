package com.capstone.favouriteService.config;

import com.capstone.favouriteService.filter.JWTValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.GenericFilterBean;

@Configuration
public class FilterConfig {

    public static final String API_URL = "/api/v1/favourite/*";

    @Bean
    public FilterRegistrationBean<GenericFilterBean> jwtFilter(){
        FilterRegistrationBean<GenericFilterBean> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new JWTValidationFilter());

        // if any url of below pattern then check for authorization otherwise dont check
        // we can add multiple patterns
        filterRegistrationBean.addUrlPatterns(API_URL);


        return filterRegistrationBean;
    }
}
