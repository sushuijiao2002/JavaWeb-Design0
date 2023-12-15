package com.example.javaweb1.cogfigure;

import com.example.javaweb1.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.ArrayList;
import java.util.List;

/*处理器拦截器的注册*/


@Configuration
public class LoginInterceptorConfigure implements WebMvcConfigurer {

/*配置拦截器*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/bootstrap/**")
                .excludePathPatterns("/img/*")
                .excludePathPatterns("/jquery/*")
                .excludePathPatterns("/webjars/*")
                .excludePathPatterns("/LoginRegister.html")
/*                .excludePathPatterns("/PersonInfo.html")
                .excludePathPatterns("/ChangePassword.html")
                .excludePathPatterns("/UploadHead.html")
                .excludePathPatterns("/WorkList.html")*/
                .excludePathPatterns("/user/register")
/*                .excludePathPatterns("/user/changeInfo")*/
                .excludePathPatterns("/user/login");

//        HandlerInterceptor interceptor = new LoginInterceptor();
/*配置白名单*/

/**/
//        List<String> patterns = new ArrayList<>();
//        patterns.add("/static/**");
//        patterns.add("/static/LoginRegister.html");
//        patterns.add("/static/index.html");
//        patterns.add("/user/register");
//        patterns.add("/user/login");
////完成拦截器的注册
//
//
//        registry.addInterceptor(interceptor).addPathPatterns("/**").excludePathPatterns(patterns);
        //WebMvcConfigurer.super.addInterceptors(registry);
    }
}
