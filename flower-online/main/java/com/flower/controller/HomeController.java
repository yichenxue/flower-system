package com.flower.controller;

import com.flower.entity.User;
import com.flower.service.UserService;
import com.flower.util.VertityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yumaoying on 2018/5/6.
 */
@Controller
public class HomeController {
    @Autowired
    private UserService userService;


    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }


    //生成短信验证码图片
    @GetMapping("/createImage")
    public void createImage(HttpServletResponse response, HttpSession session) throws IOException {
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VertityUtil.createImage();
        //将验证码存入Session
        session.setAttribute("validateCode", objs[0]);
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
}
