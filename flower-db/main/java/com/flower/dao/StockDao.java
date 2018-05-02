package com.flower.dao;

import com.flower.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by yumaoying on 2018/4/30
 * 库存dao
 */
public interface StockDao extends JpaRepository<Stock, Integer>, JpaSpecificationExecutor<Stock> {

    public Page<Stock> findAll(Specification spec, Pageable pageable);

    public void delete(Integer id);

    public Stock save(Stock stock);

    public Stock findById(Integer id);

    public Stock findByGoods_GoodsId(Integer goodId);

}