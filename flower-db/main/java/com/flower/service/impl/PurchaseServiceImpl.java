package com.flower.service.impl;

import com.flower.dao.PurchaseDao;
import com.flower.entity.Purchase;
import com.flower.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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

    public Purchase save(Purchase purchase) {
        return purchaseDao.save(purchase);
    }

    @Override
    public Page<Purchase> findAll(Purchase purchase, Pageable pageable) {
        System.out.println("====================插询开始:");
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
                System.out.println("===========================xyhkkh");
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        System.out.println("===========================lioooo");
        return purchaseDao.findAll(specification, pageable);
    }

    public void delete(Integer id) {
        purchaseDao.delete(id);
    }

    public Purchase findById(Integer id) {
        return purchaseDao.findById(id);
    }
}
