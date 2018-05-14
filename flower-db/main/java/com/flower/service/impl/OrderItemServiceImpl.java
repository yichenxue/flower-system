package com.flower.service.impl;

import com.flower.dao.OrderDetailDao;
import com.flower.dao.OrderItemDao;
import com.flower.entity.OrderDetail;
import com.flower.entity.OrderItem;
import com.flower.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by yumaoying on 2018/5/14.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    //使用事务，若出现错误，回退
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public OrderItem save(OrderItem orderItem) {
        OrderItem orderItem1 = orderItemDao.save(orderItem);
        for (OrderDetail orderDetail : orderItem.getOrderDetails()) {
            orderDetail.setOrderItem(orderItem1);
            orderDetailDao.save(orderDetail);
        }
        return orderItem;
    }

}
