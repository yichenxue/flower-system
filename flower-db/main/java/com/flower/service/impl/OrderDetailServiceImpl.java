package com.flower.service.impl;

import com.flower.dao.OrderDetailDao;
import com.flower.dao.StockDao;
import com.flower.entity.OrderDetail;
import com.flower.service.OrderDetailService;
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
 * Created by yumaoying on 2018/5/14.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private StockDao stockDao;


    @Override
    public List<OrderDetail> findAll() {
        return orderDetailDao.findAll();
    }

    //按订单生成时间降序排
    @Override
    public List<OrderDetail> findAllOrderByOrderDateDesc() {
        return orderDetailDao.findAllOrderByOrderDateDesc();
    }

    @Transactional
    public void delete(Integer id) {
        orderDetailDao.delete(id);
    }

    public OrderDetail findById(Integer id) {
        return orderDetailDao.findById(id);
    }

    public OrderDetail findByOrderNo(String orderNo) {
        return orderDetailDao.findByOrderNo(orderNo);
    }

    @Transactional
    public void updateOrderStatus(String status, String orderPayWay, Integer orderParentId) {
        orderDetailDao.updateOrderStatus(status, orderPayWay, orderParentId);
    }

    //子订单单独付款
    @Transactional
    public void orderDetailPay(OrderDetail orderDetail, String orderPayWay) {
        orderDetailDao.updateStatusAndOrderPayWay("03", orderPayWay, orderDetail.getId());
        OrderDetail orderDetail1 = orderDetailDao.findById(orderDetail.getId());
        //查找原商品库存
        Integer oriNum = stockDao.findStockNumByGoods_GoodsId(orderDetail1.getGoods().getGoodsId());
        //修改商品库存,减少
        stockDao.updateStockNum(orderDetail1.getGoods().getGoodsId(), oriNum - orderDetail1.getOrderNumber());
    }

    //分页查找订单
    public Page<OrderDetail> findAll(OrderDetail orderDetail, Pageable pageable) {
        Specification<OrderDetail> specification = new Specification<OrderDetail>() {
            @Override
            public Predicate toPredicate(Root<OrderDetail> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (orderDetail.getOrderNo() != null && !orderDetail.getOrderNo().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("orderNo").as(String.class), "%" + orderDetail.getOrderNo() + "%"));
                }
                if (orderDetail.getId() != null && !orderDetail.getId().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("id").as(Integer.class), orderDetail.getId()));
                }
                if (orderDetail.getOrderStatus() != null && !orderDetail.getOrderStatus().toString().trim().isEmpty()) {
                    predicates.add(cb.equal(root.get("orderStatus").as(String.class), orderDetail.getOrderStatus()));
                }
                if (orderDetail.getOrderDate() != null && !orderDetail.getOrderDate().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("orderDate").as(String.class), "%" + orderDetail.getOrderDate() + "%"));
                }
                if (orderDetail.getOrderUserName() != null && !orderDetail.getOrderUserName().trim().isEmpty()) {
                    predicates.add(cb.like(root.get("orderUserName").as(String.class), "%" + orderDetail.getOrderUserName() + "%"));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        return orderDetailDao.findAll(specification, pageable);

    }

    //更新订单状态
    @Transactional
    public void updateOrderStatus(Integer orderId, String orderStatus) {
        orderDetailDao.updateOrderStatus(orderId, orderStatus);
    }


    //更新或保存订单
    @Transactional
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailDao.save(orderDetail);
    }
}
