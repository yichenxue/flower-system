package com.flower.dao;

import com.flower.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by yumaoying on 2018/5/1.
 * 供应商dao层
 */
public interface SupplierDao extends JpaRepository<Supplier, Integer>, JpaSpecificationExecutor<Supplier> {
    //保存供应商信息
    public Supplier save(Supplier supplier);

    public Page<Supplier> findAll(Specification spec, Pageable pageable);

    public void delete(Integer id);

    public Supplier findBySupId(Integer id);

    public List<Supplier> findAll();
}
