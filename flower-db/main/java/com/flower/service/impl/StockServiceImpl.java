package com.flower.service.impl;

import com.flower.dao.PurchaseDao;
import com.flower.dao.StockDao;
import com.flower.dao.SupplierDao;
import com.flower.entity.Goods;
import com.flower.entity.Purchase;
import com.flower.entity.Stock;
import com.flower.entity.Supplier;
import com.flower.service.StockService;
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
 * 库存管理业务层
 */
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockDao stockDao;

    @Autowired
    private SupplierDao supplierDao;

    @Autowired
    private PurchaseDao purchaseDao;

    //分页查找
    public Page<Stock> findAll(Stock stock, Pageable pageable) {
        Specification<Stock> specification = new Specification<Stock>() {
            @Override
            public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (stock.getId() != null && !stock.getId().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("stockId").as(Integer.class), stock.getId()));
                }
                if (stock.getStockNum() != null && !stock.getStockNum().toString().trim().isEmpty()) {
                    predicates.add(cb.le(root.get("stockNum").as(Integer.class), stock.getStockNum()));
                }
                if (stock.getIsAlarm() != null && !stock.getIsAlarm().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("isAlarm").as(Integer.class), stock.getIsAlarm()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return stockDao.findAll(specification, pageable);
    }

    //删除
    @Transactional
    public void delete(Integer id) {
        stockDao.delete(id);
    }

    //保存
    @Transactional
    public Stock save(Stock stock) {
        return stockDao.save(stock);
    }

    public Stock findById(Integer id) {
        return stockDao.findById(id);
    }

    //根据商品id查找库存量
    public Stock findByGoods_GoodsId(Integer goodId) {
        return stockDao.findByGoods_GoodsId(goodId);
    }

    @Transactional
    public void save(Purchase purchase, Goods goods, Supplier supplier) {
        purchase.setGoods(goods);
        Supplier s = supplierDao.save(supplier);
        purchase.setSupplier(s);
        purchaseDao.save(purchase);
    }
}
