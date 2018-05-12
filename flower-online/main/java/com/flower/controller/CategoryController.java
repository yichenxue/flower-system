package com.flower.controller;

import com.flower.entity.Category;
import com.flower.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yumaoying on 2018/5/12.
 */
@Controller
@RequestMapping({"/category"})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //获取分类树,前端显示
    @RequestMapping("categorys")
    @ResponseBody
    public String findAll() {
        String strJson = getCategoryByparentId(1);
        strJson = strJson.substring(0, strJson.length() - 1);
        return strJson;
    }


    public String getCategoryByparentId(Integer parentId) {
        String sonNodes = "";
        List<Category> list = categoryService.findByParentId(parentId);
        for (Category category : list) {
            sonNodes += "{text:'" + category.getCategoryName() + "',id:'" + category.getCategoryId() + "'";
            if (!getCategoryByparentId(category.getCategoryId()).isEmpty()) {
                sonNodes += ",nodes:[" + getCategoryByparentId(category.getCategoryId()) + "]";
            }
            sonNodes += "},";
        }
        return sonNodes;
    }

    @RequestMapping("categorysWithLevel")
    @ResponseBody
    public List<Category> findAllCategory() {
        Map map = new HashMap<>();
        List<Category> list = categoryService.findAll();
        List<Category> clevel = new ArrayList<Category>();
        int lev = 0;
        getCategoryWithLevel(list, clevel, 0, lev);
        return clevel;
    }


    public void getCategoryWithLevel(List<Category> clist, List<Category> clevel, int id, int lev) {
        for (Category category : clist) {
            if (category.getParentId() == id) {
                Category c = category;
                c.setLevel(lev);
                clevel.add(c);
                getCategoryWithLevel(clist, clevel, category.getCategoryId(), lev + 1);
            }
        }
    }

}
