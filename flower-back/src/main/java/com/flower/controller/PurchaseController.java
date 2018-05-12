package com.flower.controller;

import com.flower.entity.Goods;
import com.flower.entity.Purchase;
import com.flower.entity.Supplier;
import com.flower.service.GoodsService;
import com.flower.service.PurchaseService;
import com.flower.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yumaoying on 2018/5/1.
 * 采购管理
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SupplierService supplierService;

    @RequestMapping
    public String purchasePage() {
        return "stock/purchases";
    }

    @RequestMapping("/purchases")
    @ResponseBody
    public Map<String, Object> get(Purchase purchase, String draw,
                                   @RequestParam(required = false, defaultValue = "0") int start,
                                   @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "id"); //按采购时间降序排
        Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = purchaseService.findAll(purchase, pageable);
        System.out.println("查询到的信息+" + page.getTotalElements() + "," + page.getTotalElements());
        map.put("draw", draw);
        map.put("recordsTotal", page.getTotalElements());
        map.put("recordsFiltered", page.getTotalElements());
        map.put("data", page.getContent());
        return map;
    }

    @RequestMapping("/toAddPurchase")
    @ResponseBody
    public Map<String, Object> toAddPurchase() {
        Map<String, Object> map = new HashMap<>();
        map.put("glist", goodsService.findAll());
        map.put("slist", supplierService.findAll());
        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Purchase findById(Integer id) {
        return purchaseService.findById(id);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String save(Purchase purchase, Goods goods, Supplier supplier) {
        try {
            purchase.setGoods(goods);
            purchase.setSupplier(supplier);
            purchaseService.save(purchase);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "保存错误!";
        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Purchase purchase, Goods goods, Supplier supplier, Integer oriPurchaseNumber) {
        try {
            System.out.println("purchase:" + purchase + ",goods:" + goods + ",supplier" + supplier);
            purchase.setGoods(goods);
            purchase.setSupplier(supplier);
            purchaseService.edit(purchase, oriPurchaseNumber);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "修改失败!";
        }

    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        try {
            purchaseService.delete(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "删除失败";
            else
                return "删除错误!";
        }
    }
}
