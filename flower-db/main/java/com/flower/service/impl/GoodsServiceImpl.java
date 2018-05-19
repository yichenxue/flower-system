package com.flower.service.impl;

import com.flower.dao.GoodsDao;
import com.flower.dao.StockDao;
import com.flower.entity.Category;
import com.flower.entity.Goods;
import com.flower.entity.Stock;
import com.flower.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yumaoying on 2018/4/30.
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private StockDao stockDao;

    public Page<Goods> findAll(Goods goods, Pageable pageable) {
        Specification<Goods> specification = new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (goods.getGoodsName() != null && !goods.getGoodsName().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("goodsName").as(String.class), "%" + goods.getGoodsName() + "%"));
                }
                if (goods.getGoodsShijia() != null && !goods.getGoodsShijia().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("goodsShijia").as(BigDecimal.class), goods.getGoodsPurchasePrice()));
                }
                if (goods.getGoodsPrice() != null && !goods.getGoodsPrice().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("goodsPrice").as(BigDecimal.class), goods.getGoodsPurchasePrice()));
                }
                if (goods.getGoodsPurchasePrice() != null && !goods.getGoodsPurchasePrice().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("goodsPurchasePrice").as(BigDecimal.class), goods.getGoodsPurchasePrice()));
                }
                if (goods.getGoodsDiscount() != null && !goods.getGoodsDiscount().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("goodsDiscount").as(BigDecimal.class), goods.getGoodsPurchasePrice()));
                }
                if (goods.getGoodsId() != null && !goods.getGoodsId().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("goodsId").as(BigDecimal.class), goods.getGoodsId()));
                }
                if (goods.getGoodsMean() != null && !goods.getGoodsMean().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("goodsMean").as(BigDecimal.class), goods.getGoodsId()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return goodsDao.findAll(specification, pageable);
    }

    public Goods findByGoodsId(Integer id) {
        return goodsDao.findByGoodsId(id);
    }

    @Transactional
    public void save(Goods goods) {
        Goods g = goodsDao.save(goods);
        //添加商品时，默认添加一条库存信息
        Stock stock = new Stock();
        //默认不报警
        stock.setIsAlarm(0);
        //默认库存设置为0
        stock.setStockNum(0);
        stock.setGoods(g);
        stockDao.save(stock);
    }

    @Transactional
    public void edit(Goods goods) {
        goodsDao.save(goods);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void delete(Integer goodsId, Integer stockId) {
        //删除库存信息
        stockDao.delete(stockId);
        //删除商品信息
        goodsDao.delete(goodsId);
    }

    public List<Goods> findAll() {
        return goodsDao.findAll();
    }

    //根据商品id查询商品类别
    public List<Category> findCategorysByGoodsId(Integer id) {
        return goodsDao.findCategorysByGoodsId(id);
    }

    //保存分类
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void saveCategorys(Integer goodsId, String categoryIds) {
        //先删除原有分类关系
        goodsDao.deleteCategorys(goodsId);
        String[] cs = categoryIds.split(",");
        //保存
        for (int i = 0; i < cs.length; i++) {
            goodsDao.save(goodsId, Integer.parseInt(cs[i]));
        }
    }
}
