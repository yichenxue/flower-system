package com.flower.controller;

import com.flower.entity.Supplier;
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
 * 供应商管理
 */
@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    //进入供应商页面
    @RequestMapping
    public String supplierPage() {
        return "/supplier/suppliers";
    }

    @RequestMapping("/suppliers")
    @ResponseBody
    public Map<String, Object> get(Supplier supplier, String draw,
                                   @RequestParam(required = false, defaultValue = "0") int start,
                                   @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "supId");
        Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = supplierService.findAll(supplier, pageable);
        map.put("draw", draw);
        map.put("recordsTotal", page.getTotalElements());
        map.put("recordsFiltered", page.getTotalElements());
        map.put("data", page.getContent());
        return map;
    }


    @RequestMapping("/findById")
    @ResponseBody
    public Supplier findById(Integer id) {
        return supplierService.findById(id);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String add(Supplier supplier) {
        try {
            supplierService.save(supplier);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "保存失败!";
        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Supplier supplier) {
        try {
            supplierService.save(supplier);
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
            supplierService.delete(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "删除失败,该供应商与采购信息相关,暂无法删除!";
            else
                return "删除错误!";
        }
    }
}
