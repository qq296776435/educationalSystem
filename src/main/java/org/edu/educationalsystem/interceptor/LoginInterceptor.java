package org.edu.educationalsystem.interceptor;

import org.edu.educationalsystem.Domain.Instructor;
import org.edu.educationalsystem.User;
import org.edu.educationalsystem.service.LoginService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class LoginInterceptor implements HandlerInterceptor{
    @Resource
    private LoginService loginService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("SecurityInterceptor...preHandle...");
        User user = (User) request.getSession().getAttribute("user");


        if (user!=null){

            if (loginService.login(new Instructor(user.getId(),"1"))!=null){
                return true;
            }
        }
        response.getWriter().print("You are not a administrator!!!");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
