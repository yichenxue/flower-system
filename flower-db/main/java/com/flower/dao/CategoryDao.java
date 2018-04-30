package com.flower.dao;

import com.flower.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by yumaoying on 2018/4/30.
 * 商品分类
 */
public interface CategoryDao extends JpaRepository<Category, Integer>, JpaSpecificationExecutor<Category> {

    public Category save(Category category);

    public void delete(Integer categoryId);

    public List<Category> findAll();

    public Category findByCategoryId(Integer categoryId);

    public Page<Category> findAll(Specification<Category> spec, Pageable pageable);
}
