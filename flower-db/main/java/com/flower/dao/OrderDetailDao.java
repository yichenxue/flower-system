package com.flower.dao;


import com.flower.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by yumaoying on 2018/5/14.
 */
public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer>, JpaSpecificationExecutor<OrderDetail> {
    //保存订单信息
    public OrderDetail save(OrderDetail orderDetail);

    //删除订单
    public void delete(Integer id);

    //根据id查找订单
    public OrderDetail findById(Integer id);

    //根据生成的订单编号查找
    public OrderDetail findByOrderNo(String orderNo);

    //查找所有订单
    public List<OrderDetail> findAll();

    //查找所有订单，根据时间降序排序
    @Query("select  t from OrderDetail t order by t.orderDate desc")
    public List<OrderDetail> findAllOrderByOrderDateDesc();

    @Modifying
    @Query(value = "update order_detail t set  t.order_status=?1,t.order_pay_way=?2 where t.id=?3", nativeQuery = true)
    public void updateStatusAndOrderPayWay(String status, String orderPayWay, Integer id);

    //根据父订单,更新订单状态和付款方式
    @Modifying
    @Query(value = "update order_detail t set  t.order_status=?1,t.order_pay_way=?2 where t.order_parent_id=?3", nativeQuery = true)
    public void updateOrderStatus(String status, String orderPayWay, Integer orderParentId);

    //删除父订单下的子订单
    @Modifying
    @Query(value = "DELETE  FROM order_detail  WHERE order_parent_id=?1", nativeQuery = true)
    public void deleteParsentId(Integer orderItemId);
}
