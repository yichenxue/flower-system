package com.flower.dao;

import com.flower.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
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

    //查找商品库存量
    @Query(value = "select stock_num from stock  where goods_id=?1", nativeQuery = true)
    public Integer findStockNumByGoods_GoodsId(Integer goodId);

    //更新商品库存
    @Query(value = "update stock set stock_num=?2 where goods_id=?1", nativeQuery = true)
    @Modifying
    public void updateStockNum(Integer goodsId, Integer stockNum);
}