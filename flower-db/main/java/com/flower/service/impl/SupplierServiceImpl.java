package com.flower.service.impl;

import com.flower.dao.SupplierDao;
import com.flower.entity.Goods;
import com.flower.entity.Supplier;
import com.flower.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yumaoying on 2018/5/1.
 * 供应商管理业务层实现
 */
@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierDao supplierDao;

    //分页查找供应商信息
    public Page<Supplier> findAll(Supplier supplier, Pageable pageable) {
        Specification<Supplier> specification = new Specification<Supplier>() {
            @Override
            public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (supplier.getSupId() != null && !supplier.getSupId().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("supId").as(String.class), supplier.getSupId()));
                }
                if (supplier.getSupName() != null && !supplier.getSupName().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("supName").as(String.class), "%" + supplier.getSupName() + "%"));
                }
                if (supplier.getSupAddress() != null && !supplier.getSupAddress().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("supAddress").as(String.class), "%" + supplier.getSupAddress() + "%"));
                }
                if (supplier.getSupTel() != null && !supplier.getSupTel().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("supTel").as(String.class), "%" + supplier.getSupTel() + "%"));
                }
                if (supplier.getSupEmail() != null && !supplier.getSupEmail().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("supEmail").as(String.class), "%" + supplier.getSupEmail() + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return supplierDao.findAll(specification, pageable);
    }

    //根据id删除供应商信息
    public void delete(Integer id) {
        supplierDao.delete(id);
    }

    public Supplier save(Supplier supplier) {
        return supplierDao.save(supplier);
    }

    public Supplier findById(Integer id) {
        return supplierDao.findBySupId(id);
    }

    public List<Supplier> findAll() {
        return supplierDao.findAll();
    }
}
