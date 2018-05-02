package com.flower.dao;

import com.flower.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by yumaoying on 2018/5/1.
 * 采购dao
 */
public interface PurchaseDao extends JpaRepository<Purchase, Integer>, JpaSpecificationExecutor<Purchase> {

    public List<Purchase> findAll();

    public Purchase save(Purchase purchase);

    @Override
    public Page<Purchase> findAll(Specification specification, Pageable pageable);

    public void delete(Integer id);

    public Purchase findById(Integer id);
}
