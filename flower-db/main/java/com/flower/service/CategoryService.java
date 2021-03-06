package com.flower.service;

import com.flower.entity.Category;
import com.flower.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yumaoying on 2018/4/30.
 */
public interface CategoryService {
    public void save(Category category);

    public void delete(Integer categoryId);

    public List<Category> findAll();

    public Category findByCategoryId(Integer categoryId);

    public Page<Category> findAll(Category category, Pageable pageable);

    public List<Category> findByParentId(Integer id);

    public List<Goods> findGoodsByCategoryId(Integer categoryId);
}
