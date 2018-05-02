package com.flower.service;

import com.flower.entity.Goods;
import com.flower.entity.Purchase;
import com.flower.entity.Stock;
import com.flower.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yumaoying on 2018/4/30.
 * 库存管理业务层接口
 */
public interface StockService {
    public Page<Stock> findAll(Stock stock, Pageable pageable);

    public void delete(Integer id);

    public Stock save(Stock stock);

    public Stock findById(Integer id);

    public Stock findByGoods_GoodsId(Integer goodId);

    public void save(Purchase purchase, Goods goods, Supplier supplier);
}
