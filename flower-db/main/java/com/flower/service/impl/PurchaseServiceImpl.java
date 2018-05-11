package com.flower.service.impl;

import com.flower.dao.PurchaseDao;
import com.flower.dao.StockDao;
import com.flower.entity.Purchase;
import com.flower.service.PurchaseService;
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
 * Created by yumaoying on 2018/5/1.
 * 采购业务处理接口
 */
@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private PurchaseDao purchaseDao;

    @Autowired
    private StockDao stockDao;

    @Transactional
    public Purchase save(Purchase purchase) {
        //商品采购时，需要改变商品库存量
        //获取库存量
        Integer stockNum = stockDao.findStockNumByGoods_GoodsId(purchase.getGoods().getGoodsId());
        stockDao.updateStockNum(purchase.getGoods().getGoodsId(), purchase.getPurchaseNumber() + stockNum);
        return purchaseDao.save(purchase);
    }

    /***
     * 修改采购信息
     * @param purchase 采购信息
     * @param oriPurchaseNumber 原采购量
     * @return 保存后的采购信息
     */
    @Transactional
    public Purchase edit(Purchase purchase, Integer oriPurchaseNumber) {
        //获取库存量
        Integer stockNum = stockDao.findStockNumByGoods_GoodsId(purchase.getGoods().getGoodsId());
        //未采购前的数量
        Integer num = stockNum - oriPurchaseNumber;
        stockDao.updateStockNum(purchase.getGoods().getGoodsId(), purchase.getPurchaseNumber() + num);
        return purchaseDao.save(purchase);
    }

    @Override
    public Page<Purchase> findAll(Purchase purchase, Pageable pageable) {
        Specification<Purchase> specification = new Specification<Purchase>() {
            @Override
            public Predicate toPredicate(Root<Purchase> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (purchase.getId() != null && !purchase.getId().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("id").as(Integer.class), purchase.getId()));
                }
                if (purchase.getPurchaseDate() != null && !purchase.getPurchaseDate().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("purchaseDate").as(String.class), purchase.getPurchaseDate()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return purchaseDao.findAll(specification, pageable);
    }

    public void delete(Integer id) {
        purchaseDao.delete(id);
    }

    public Purchase findById(Integer id) {
        return purchaseDao.findById(id);
    }


}
