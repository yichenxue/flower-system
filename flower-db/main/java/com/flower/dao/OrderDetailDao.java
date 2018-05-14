package com.flower.dao;


import com.flower.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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
}
