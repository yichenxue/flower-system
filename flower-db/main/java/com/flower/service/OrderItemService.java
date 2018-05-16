package com.flower.service;

import com.flower.entity.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by yumaoying on 2018/5/14.
 */
public interface OrderItemService {

    public OrderItem save(OrderItem orderItem);

    //根据系统生成的订单号查找
    public OrderItem findByOrderNo(String orderNo);

    //一次下单多个订单支付
    public void orderPay(OrderItem orderItem, String orderPayWay);

    //删除用户总订单信息
    public void deleteOrderItem(Integer orderItemId);

    public Page<OrderItem> findAll(OrderItem orderItem, Pageable pageable);

    public OrderItem findByOrderId(Integer orderId);
}
