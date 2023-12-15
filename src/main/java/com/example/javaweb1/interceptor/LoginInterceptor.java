package com.example.javaweb1.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/*拦截器*/

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object object = request.getSession().getAttribute("id");

        /*用户没有登录过系统，重定向到LoginRegister.html*/
        if (object == null){
            response.sendRedirect("/LoginRegister.html");
            return false;
        }
        return true;
    }
}
