package com.flower.dao;


import com.flower.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by yumaoying on 2018/5/14.
 */
public interface OrderItemDao extends JpaRepository<OrderItem, Integer>, JpaSpecificationExecutor<OrderItem> {

    public OrderItem save(OrderItem orderItem);
}
