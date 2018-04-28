package com.flower.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/3/25.
 * 订单信息
 */
@Entity
public class Order {
    private int orderId; //编号
    private String orderNo;  //订单号
    private Integer orderUserId; //订单用户id
    private Integer orderNumber; //订单总量
    private Double orderAmount; //订单总额
    private Timestamp orderDate; //订单日期
    private Collection<OrderDetail> orderDetails;

    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "order_no", nullable = true, length = 100)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "order_user_id", nullable = true)
    public Integer getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }

    @Basic
    @Column(name = "order_number", nullable = true)
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Basic
    @Column(name = "order_amount", nullable = true, precision = 0)
    public Double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Basic
    @Column(name = "order_date", nullable = true)
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                Objects.equals(orderNo, order.orderNo) &&
                Objects.equals(orderUserId, order.orderUserId) &&
                Objects.equals(orderNumber, order.orderNumber) &&
                Objects.equals(orderAmount, order.orderAmount) &&
                Objects.equals(orderDate, order.orderDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, orderNo, orderUserId, orderNumber, orderAmount, orderDate);
    }

    @OneToMany(mappedBy = "order")
    public Collection<OrderDetail> getorderDetails() {
        return orderDetails;
    }

    public void setorderDetails(Collection<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
