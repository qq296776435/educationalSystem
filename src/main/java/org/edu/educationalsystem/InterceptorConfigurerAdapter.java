package org.edu.educationalsystem;

import org.edu.educationalsystem.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class InterceptorConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }//without this, loginInterceptor will be loaded after SpringContext(LoginService)
    //and @Resource won't work
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor()).addPathPatterns("/admin");
        super.addInterceptors(registry);
    }
}

