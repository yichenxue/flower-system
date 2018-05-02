package com.flower.controller;

import com.flower.entity.Goods;
import com.flower.entity.Purchase;
import com.flower.entity.Stock;
import com.flower.entity.Supplier;
import com.flower.service.PurchaseService;
import com.flower.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yumaoying on 2018/4/30.
 * 库存管理
 */
@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping
    public String stockPage() {
        return "stock/stocks";
    }

    @RequestMapping("/stocks")
    @ResponseBody
    public Map<String, Object> get(Stock stock, String draw,
                                   @RequestParam(required = false, defaultValue = "0") int start,
                                   @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = stockService.findAll(stock, pageable);
        map.put("draw", draw);
        map.put("recordsTotal", page.getTotalElements());
        map.put("recordsFiltered", page.getTotalElements());
        map.put("data", page.getContent());
        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Stock findById(Integer id) {
        return stockService.findById(id);
    }


    @PostMapping("/edit")
    @ResponseBody
    public String edit(Stock stock, Integer goodsId) {
        try {
            Goods g = new Goods();
            g.setGoodsId(goodsId);
            stock.setGoods(g);
            stockService.save(stock);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "该花卉对应的库存信息已存在!";
            return "保存失败";
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        try {
            stockService.delete(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
