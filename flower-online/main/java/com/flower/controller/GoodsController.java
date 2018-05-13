package com.flower.controller;

import com.flower.entity.Goods;
import com.flower.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by yumaoying on 2018/5/7.
 */
@Controller
@RequestMapping({"/goods"})
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    //根据商品id查找鲜花信息
    @RequestMapping({"/findById"})
    public String getGoods(Integer id, Model model) {
        Goods goods = goodsService.findByGoodsId(id);
        model.addAttribute("goods", goods);
        return "flowerInfo";
    }
}
