package com.flower.controller;

import com.flower.entity.User;
import com.flower.service.UserService;
import com.flower.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by yumaoying on 2018/5/6.
 * 用户
 */
@Controller
public class UserController {
    @Value("${web.upload-path}")
    private String path;

    @Autowired
    private UserService userService;

    //进入注册页
    @RequestMapping("/toRegister")
    public String toRegister() {
        return "register";
    }

    //进入登陆页面
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    //退出登陆
    @RequestMapping("/logout")
    public String logoutOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/login";
    }

    @RequestMapping("/userInfo")
    public String toUserInfo(HttpServletRequest request) {
        if (request.getSession().getAttribute("loginUser") == null) {
            return "redirect:toLogin";
        }
        return "/userInfo";
    }

    @RequestMapping("/register")
    @ResponseBody
    public String register(User user) {
        try {
            System.out.println("==========user" + user);
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            user.setUserRegister(time.format(new Date()));
            userService.saveUser(user);
            return "注册成功!";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "用户已存在,请检查是账户名,邮箱,手机号!";
            return "注册失败!";
        }
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(User user, String randomCode, String rememberMe, HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("==============" + user + ",randomCode:" + randomCode);
            String validateCode = (String) request.getSession().getAttribute("validateCode");
            if (!randomCode.equalsIgnoreCase(validateCode)) {
                return "验证码不正确!";
            }
            User loginUser = userService.findByUserEamilOrUserTelOrUserName(user.getUserName(), user.getUserName(), user.getUserName());
            if (loginUser == null) {
                return "用户不存在!";
            } else if (loginUser.getUserPw().equals(user.getUserPw())) {
                //更新用户登陆时间
                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                userService.updateLast(user.getUserId(), time.format(new Date()));
                request.getSession().setAttribute("loginUser", loginUser);
                //记住我功能的实现
                Cookie nameCookie = new Cookie("flower_name", user.getUserName());
                Cookie passwordCookie = new Cookie("flower_userId", loginUser.getUserId().toString());
                //设置cookie路径
                nameCookie.setPath(request.getContextPath() + "/");
                passwordCookie.setPath(request.getContextPath() + "/");
                if (rememberMe != null && "1".equals(rememberMe)) {
                    //以秒计算
                    nameCookie.setMaxAge(60);
                    passwordCookie.setMaxAge(60);
                } else {
                    nameCookie.setMaxAge(0);
                    passwordCookie.setMaxAge(0);
                }
                //将cookie设置到response
                response.addCookie(nameCookie);
                response.addCookie(passwordCookie);
                return "登陆成功!";
            } else {
                return "密码错误!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "系统错误!";
        }
    }


    @PostMapping("/edit")
    @ResponseBody
    public String edit(User user, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        try {

            System.out.println("====================user" + user);
            String msg = FileUploadUtil.upload(path, file);
            if ("图片格式错误".equals(msg) && "图片上传错误".equals(msg)) {
                return msg;
            }
            if (!msg.trim().isEmpty()) {
                user.setUserImg(msg);
            }
            User loginUser = (User) request.getSession().getAttribute("loginUser");
            // 密码和注册时间不更改
            user.setUserPw(loginUser.getUserPw());
            user.setUserRegister(loginUser.getUserRegister());
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //修改登陆时间
            user.setUserLast(time.format(new Date()));
            User u = userService.saveUser(user);
            System.out.println("============================u" + u);
            //   request.getSession().setAttribute("loginUser",u);
            return "修改成功";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "用户信息已存在，请检查用户名，邮箱，手机号";
            return "修改失败";
        }
    }
}
