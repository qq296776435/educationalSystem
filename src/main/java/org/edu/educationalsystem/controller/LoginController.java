package org.edu.educationalsystem.controller;

import org.edu.educationalsystem.Domain.Student;
import org.edu.educationalsystem.service.LoginService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Resource
    private LoginService loginService;
    @RequestMapping(value = "/")
    public String index(){
        return "login";
    }
//    @RequestMapping(value = "loginVerify")
//    public String login(String user_name,String user_password){
//        if (loginService.login(new Student(user_name,user_password))!=null){
//            return "student";
//        }
//        else return "index";
//    }
    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        Student student = loginService.login(new Student(username,password));
        System.out.print(student.getName());
        if(student==null) {
            map.put("code",0);
            map.put("msg","用户名无效！");
        } else if(!student.getId().equals(password)) {
            map.put("code",0);
            map.put("msg","密码错误！");
        } else {
            //登录成功
            map.put("code",1);
            map.put("msg","");
            //添加session
            request.getSession().setAttribute("user", student);
            //添加cookie
            if(rememberme!=null) {
                //创建两个Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);
                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
        }
        String result = new JSONObject(map).toString();
        return result;
    }
}
