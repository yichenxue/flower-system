package com.flower.service;

import com.flower.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by yumaoying on 2018/5/1.
 */
public interface SupplierService {

    public Page<Supplier> findAll(Supplier supplier, Pageable pageable);

    //根据id删除供应商信息
    public void delete(Integer id);

    public Supplier save(Supplier supplier);

    public Supplier findById(Integer id);

    public List<Supplier> findAll();
}
