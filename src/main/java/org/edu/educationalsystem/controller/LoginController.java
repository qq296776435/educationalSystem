package org.edu.educationalsystem.controller;

import com.google.gson.Gson;
import org.edu.educationalsystem.Domain.*;
import org.edu.educationalsystem.User;
import org.edu.educationalsystem.service.LoginService;
import org.edu.educationalsystem.service.MainService;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Resource
    private LoginService loginService;
    @Resource
    private MainService mainService;
    @RequestMapping(value = "/")
    public String login(Model model,HttpSession session){
        if (session.getAttribute("user")!=null){
            User user=(User)session.getAttribute("user");
            model.addAttribute("username",user.getName());
            if (user.getRight()=="1")
            return "systemPage";
            else if (user.getRight()=="2")
                return "systemPage_teacher";
        }
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
        Instructor instructor = loginService.login(new Instructor(username,password));

        User user=new User();

        if (student!=null){
            user.setId(student.getId());
            user.setName(student.getName());
            user.setRight("1");
        }
        else if (instructor!=null){
            user.setId(instructor.getId());
            user.setName(instructor.getName());
            user.setRight("2");
        }
        if(student==null&&instructor==null) {
            map.put("code",0);
            map.put("msg","用户名无效！");
        } else if(!user.getId().equals(password)) {
            map.put("code",0);
            map.put("msg","密码错误！");
        } else {
            //登录成功
            System.out.print(user.getRight());
            map.put("code",user.getRight());
            map.put("msg","");
            System.out.print(user.getName()+" login!");

            //添加session
            request.getSession().setAttribute("user",user);
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
    @RequestMapping(value = "/index")
    public String index(Model model, HttpSession session){
        User user=(User) session.getAttribute("user");
        String id = ((User)session.getAttribute("user")).getId();

        List<Takes> takesList = mainService.getTakes(id);

        model.addAttribute("list",takesList);
        model.addAttribute("username",user.getName());
        model.addAttribute("ID",user.getId());
        return "systemPage";
    }
    @RequestMapping(value = "/admin")
    public String admin(Model model, HttpSession session){
        User user=(User) session.getAttribute("user");
        String id = ((User)session.getAttribute("user")).getId();

        List<Instructor> instructorList = mainService.getAllInstructors();

        model.addAttribute("list",instructorList);
        model.addAttribute("username",user.getName());
        model.addAttribute("userID",user.getId());
        return "systemPage_teacher";
    }
    @RequestMapping(value = "/logout")
    public String logout(Model model, HttpSession session){
        session.invalidate();
        return "login";
    }
    @RequestMapping(value = "/section")
    @ResponseBody
    public String section(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception {
        String id = ((User)session.getAttribute("user")).getId();
        System.out.print(id);
        List<Takes> takesList = mainService.getTakes(id);
        System.out.print(takesList.get(0).getId());
        String result = new Gson().toJson(takesList);
        return result;
    }
}
