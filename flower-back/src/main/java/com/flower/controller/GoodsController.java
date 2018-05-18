package com.flower.controller;

import com.flower.entity.Category;
import com.flower.entity.Goods;
import com.flower.entity.Stock;
import com.flower.service.CategoryService;
import com.flower.service.GoodsService;
import com.flower.service.StockService;
import com.flower.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yumaoying on 2018/4/30.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Value("${web.upload-path}")
    private String path;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private StockService stockService;

    @RequestMapping
    public String goodsPage() {
        return "goods/goods";
    }


    @RequestMapping("/goodslist")
    @ResponseBody
    public Map<String, Object> get(Goods goods, String draw,
                                   @RequestParam(required = false, defaultValue = "0") int start,
                                   @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "goodsId");
        Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = goodsService.findAll(goods, pageable);
        map.put("draw", draw);
        map.put("recordsTotal", page.getTotalElements());
        map.put("recordsFiltered", page.getTotalElements());
        map.put("data", page.getContent());
        return map;
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Goods findById(Integer id) {
        return goodsService.findByGoodsId(id);
    }

    @RequestMapping("/add")
    @ResponseBody
    public String save(Goods goods, @RequestParam("file") MultipartFile file) {
        try {
            String msg = FileUploadUtil.upload(path, file);
            if ("图片格式错误".equals(msg) && "图片上传错误".equals(msg)) {
                return msg;
            } else {
                goods.setGoodsPic(msg);
                goodsService.save(goods);
                return "success";
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "该花卉已存在";
            return "添加失败，请检查输入数字,等格式是否有误!";
        }
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit(Goods goods, @RequestParam("file") MultipartFile file) {
        try {
            String msg = FileUploadUtil.upload(path, file);
            if (!"图片格式错误".equals(msg) && !"图片上传错误".equals(msg)) {
                goods.setGoodsPic(msg);
                goodsService.save(goods);
                return "success";
            } else {
                return msg;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "该花卉已存在";
            return "修改失败";
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id) {
        try {
            Stock stock = stockService.findByGoods_GoodsId(id);
            if (stock.getStockNum() == 0) {
                goodsService.delete(id, stock.getId());
                return "success";
            }

            return "该商品库存量不为0，无法删除!";
        } catch (Exception e) {
            System.out.println("=============错误");
            e.printStackTrace();
            if (e.getMessage().contains("constraint"))
                return "该商品和其他有关联，暂无法删除!";
            return "删除错误!";
        }
    }


    //获取商品的分类
    @RequestMapping("/goodsCategory")
    @ResponseBody
    public List<Category> goodsCategory(Integer id) {
        List<Category> gcs = goodsService.findCategorysByGoodsId(id);
        List<Category> clist = categoryService.findAll();
        for (Category aClist : clist) {
            for (Category gc : gcs) {
                if (aClist.getCategoryId().equals(gc.getCategoryId())) {
                    aClist.setChecked("true");
                }
            }
        }
        return clist;
    }


    //保存商品分类
    @RequestMapping("/saveCategorys")
    @ResponseBody
    public String saveCategorys(Integer goodsId, @RequestParam("categoryId") String categoryIds) {
        if (StringUtils.isEmpty(goodsId) || StringUtils.isEmpty(categoryIds)) {
            return "error";
        }
        try {
            goodsService.saveCategorys(goodsId, categoryIds);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
