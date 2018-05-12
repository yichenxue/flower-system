package com.flower.service.impl;

import com.flower.dao.CategoryDao;
import com.flower.entity.Category;
import com.flower.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yumaoying on 2018/4/30.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Transactional
    public void save(Category category) {
        categoryDao.save(category);
    }

    @Transactional
    public void delete(Integer categoryId) {
        categoryDao.delete(categoryId);
    }


    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    public Category findByCategoryId(Integer categoryId) {
        return categoryDao.findByCategoryId(categoryId);
    }

    //分页查询
    public Page<Category> findAll(Category category, Pageable pageable) {
        Specification<Category> specification = new Specification<Category>() {
            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (category.getCategoryId() != null && !category.getCategoryId().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("categoryId").as(Integer.class), category.getCategoryId()));
                }
                if (category.getCategoryCode() != null && !category.getCategoryCode().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("categoryCode").as(String.class), "%" + category.getCategoryCode() + "%"));
                }
                if (category.getCategoryName() != null && !category.getCategoryName().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("categoryName").as(String.class), "%" + category.getCategoryName() + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return categoryDao.findAll(specification, pageable);
    }

    //根据父节点查找分类
    public List<Category> findByParentId(Integer parentId) {
        return categoryDao.findByParentId(parentId);
    }
}
