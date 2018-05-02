package com.flower.service;

import com.flower.entity.Category;
import com.flower.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by yumaoying on 2018/4/30.
 */
public interface GoodsService {
    public Page<Goods> findAll(Goods goods, Pageable pageable);

    public Goods findByGoodsId(Integer id);

    public void save(Goods goods);

    public void delete(Integer goodsId, Integer stockId);

    public List<Goods> findAll();

    public List<Category> findCategorysByGoodsId(Integer id);

    //保存分类
    public void saveCategorys(Integer goodsId, String categoryIds);
}
