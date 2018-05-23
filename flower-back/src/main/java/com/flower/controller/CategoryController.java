package com.flower.controller;

import com.flower.entity.Category;
import com.flower.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yumaoying on 2018/4/30.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping
    public String categoryPage() {
        return "category/categorys";
    }

    @RequestMapping("/categorys")
    @ResponseBody
    @Cacheable
    public Map<String, Object> get(Category category, String draw,
                                   @RequestParam(required = false, defaultValue = "0") int start,
                                   @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "categoryId");//按用户id降序排
        Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = categoryService.findAll(category, pageable);
        map.put("draw", draw);
        map.put("recordsTotal", page.getTotalElements());
        map.put("recordsFiltered", page.getTotalElements());
        map.put("data", page.getContent());
        return map;
    }

    //类别树显示
    @RequestMapping("/findAll")
    @ResponseBody
    @Cacheable
    public List<Category> findAll() {
        return categoryService.findAll();
    }

    @RequestMapping("/findById")
    @ResponseBody
    @Cacheable
    public Category findById(Integer id) {
        return categoryService.findByCategoryId(id);
    }

    @RequestMapping(path = {"/add", "/edit"})
    @ResponseBody
    public String save(Category category) {
        try {
            categoryService.save(category);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(path = "/delete")
    @ResponseBody
    public String delete(Integer id) {
        try {
            categoryService.delete(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
