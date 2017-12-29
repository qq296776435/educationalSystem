package org.edu.educationalsystem.controller;

import org.edu.educationalsystem.Domain.Student;
import org.edu.educationalsystem.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class LoginController {
    @Resource
    private LoginService loginService;
    @RequestMapping(value = "/")
    public String index(){
        return "login";
    }
    @RequestMapping(value = "login")
    public String login(String user_name,String user_password){
        if (loginService.login(new Student(user_name,user_password))!=null){
            return "student";
        }
        else return "index";
    }

}
