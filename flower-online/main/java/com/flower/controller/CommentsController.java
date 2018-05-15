package com.flower.controller;

import com.flower.entity.Comments;
import com.flower.entity.Goods;
import com.flower.entity.User;
import com.flower.service.CommentsService;
import com.flower.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yumaoying on 2018/5/15.
 */
@Controller
@RequestMapping({"/comments"})
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    @Autowired
    private GoodsService goodsService;

    //添加评价
    @RequestMapping({"/toadd"})
    public String toAddComment(Integer goodsId, Model model) {
        Goods goods = goodsService.findByGoodsId(goodsId);
        model.addAttribute("goods", goods);
        return "addComment";
    }

    @RequestMapping({"/add"})
    @ResponseBody
    public String add(Comments comments, Integer goodsId, HttpServletRequest request) {
        User loginuser = (User) request.getSession().getAttribute("loginUser");
        if (loginuser == null) {
            //若用户为登陆，先提示登陆
            //转发到登陆页面
            return "redirect:/toLogin";
        }
        Goods goods = new Goods();
        goods.setGoodsId(goodsId);
        comments.setGoods(goods);
        comments.setUser(loginuser);
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comments.setCommentDate(time.format(new Date()));
        commentsService.save(comments);
        return "评论成功";
    }
}
