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

    public List<OrderDetail> findAllOrderByOrderDateDesc();

    //更新父订单下的订单项的订单状态和付款方式
    public void updateOrderStatus(String status, String orderPayWay, Integer orderParentId);

    //子订单项付款
    public void orderDetailPay(OrderDetail orderDetail, String orderPayWay);
}
