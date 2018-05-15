package com.flower.controller;

import com.flower.entity.Goods;
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
                       @RequestParam(required = false, defaultValue = "0") String sortWay,
                       @RequestParam(required = false, defaultValue = "goodsId") String sortName,
                       @RequestParam(required = false, defaultValue = "0") int start,
                       @RequestParam(required = false, defaultValue = "1000") int length,
                       Model model) {
        Goods goods = new Goods();
        goods.setGoodsName(name);
        Sort.Direction direction = Sort.Direction.ASC;
        if (!sortWay.equals("0")) {
            //非0降序排
            direction = Sort.Direction.DESC;
        }
        Sort sort = new Sort(direction, sortName);
        Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = goodsService.findAll(goods, pageable);
        model.addAttribute("glist", page.getContent());
        return "index";
    }
}
