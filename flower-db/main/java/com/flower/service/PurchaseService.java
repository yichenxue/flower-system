package com.flower.service;

import com.flower.entity.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yumaoying on 2018/5/1.
 * 采购业务处理接口
 */
public interface PurchaseService {
    public Purchase save(Purchase purchase);

    public Page<Purchase> findAll(Purchase purchase, Pageable pageable);

    public void delete(Integer id);

    public Purchase findById(Integer id);
}
