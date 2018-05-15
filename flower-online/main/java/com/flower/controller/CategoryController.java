package com.flower.controller;

import com.flower.entity.Category;
import com.flower.entity.Goods;
import com.flower.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

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

    //根据父id获取分类
    private String getCategoryByparentId(Integer parentId) {
        StringBuilder sonNodes = new StringBuilder();
        List<Category> list = categoryService.findByParentId(parentId);
        for (Category category : list) {
            sonNodes.append("{text:'").append(category.getCategoryName()).append("',id:'").append(category.getCategoryId()).append("'");
            if (!getCategoryByparentId(category.getCategoryId()).isEmpty()) {
                sonNodes.append(",nodes:[").append(getCategoryByparentId(category.getCategoryId())).append("]");
            }
            sonNodes.append("},");
        }
        return sonNodes.toString();
    }

    @RequestMapping("categorysWithLevel")
    @ResponseBody
    public List<Category> findAllCategory() {
        List<Category> list = categoryService.findAll();
        List<Category> clevel = new ArrayList<>();
        int lev = 0;
        getCategoryWithLevel(list, clevel, 0, lev);
        return clevel;
    }


    private void getCategoryWithLevel(List<Category> clist, List<Category> clevel, int id, int lev) {
        for (Category category : clist) {
            if (category.getParentId() == id) {
                category.setLevel(lev);
                clevel.add(category);
                getCategoryWithLevel(clist, clevel, category.getCategoryId(), lev + 1);
            }
        }
    }

    //根据商品类别id查找商品信息
    @RequestMapping({"/findGoodsByCid"})
    public String findBycategory(Integer id, Model model) {
        List<Goods> glist = categoryService.findGoodsByCategoryId(id);
        model.addAttribute("glist", glist);
        return "index";
    }
}
