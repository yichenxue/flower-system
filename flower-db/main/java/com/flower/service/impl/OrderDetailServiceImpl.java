package com.flower.service.impl;

import com.flower.dao.OrderDetailDao;
import com.flower.entity.OrderDetail;
import com.flower.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yumaoying on 2018/5/14.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Override
    public List<OrderDetail> findAll() {
        return orderDetailDao.findAll();
    }

    public void delete(Integer id) {
        orderDetailDao.delete(id);
    }

    public OrderDetail findById(Integer id) {
        return orderDetailDao.findById(id);
    }

    public OrderDetail findByOrderNo(String orderNo) {
        return orderDetailDao.findByOrderNo(orderNo);
    }
}
