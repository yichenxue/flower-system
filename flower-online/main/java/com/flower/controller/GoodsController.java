package com.flower.controller;

import com.flower.entity.Car;
import com.flower.entity.Goods;
import com.flower.entity.User;
import com.flower.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    //按商品关键字模糊查询
    @RequestMapping({"/find"})
    public String find(String name,
                       @RequestParam(required = false, defaultValue = "0") int start,
                       @RequestParam(required = false, defaultValue = "1000") int length,
                       Model model) {
        Goods goods = new Goods();
        goods.setGoodsName(name);
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "goodsId");
        Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = goodsService.findAll(goods, pageable);
        model.addAttribute("glist", page.getContent());
        return "index";
    }


}
