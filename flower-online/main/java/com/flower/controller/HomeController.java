package com.flower.controller;

import com.flower.entity.Goods;
import com.flower.service.GoodsService;
import com.flower.util.VertityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by yumaoying on 2018/5/6.
 */
@Controller
public class HomeController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        List<Goods> glist = goodsService.findAll();
        model.addAttribute("glist", glist);
        return "index";
    }


    @RequestMapping({"/notices"})
    public String notices() {
        return "notices";
    }

    //生成短信验证码图片
    @GetMapping("/createImage")
    public void createImage(HttpServletResponse response, HttpSession session) throws IOException {
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VertityUtil.createImage();
        //将验证码存入Session
        session.setAttribute("validateCode", objs[0]);
        response.setContentType("image/png");
        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }
}
