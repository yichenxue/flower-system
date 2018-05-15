package com.flower.dao;


import com.flower.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by yumaoying on 2018/5/14.
 */
public interface OrderItemDao extends JpaRepository<OrderItem, Integer>, JpaSpecificationExecutor<OrderItem> {

    public OrderItem save(OrderItem orderItem);

    //根据系统生成的订单号查找
    public OrderItem findByOrderNo(String orderNo);

    @Modifying
    @Query("delete from OrderItem o where o.orderId=?1")
    public void delete(Integer orderItemId);

}
