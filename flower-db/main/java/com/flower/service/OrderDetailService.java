package com.flower.service;

import com.flower.entity.OrderDetail;

import java.util.List;

/**
 * Created by yumaoying on 2018/5/14.
 * 订单明细
 */
public interface OrderDetailService {

    public List<OrderDetail> findAll();

    public void delete(Integer id);

    public OrderDetail findById(Integer id);

    public OrderDetail findByOrderNo(String orderNo);

}
