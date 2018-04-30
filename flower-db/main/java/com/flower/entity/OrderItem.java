package com.flower.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 */
@Entity
public class OrderItem {
    private Integer orderId;
    private String orderNo;
    private Integer orderUserId;
    private Integer orderNumber;
    private BigDecimal orderAmount;
    private String orderDate;
    private List<OrderDetail> orderDetails;

    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    @Column(name = "order_no", nullable = true, length = 100)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    @Column(name = "order_user_id", nullable = true)
    public Integer getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(Integer orderUserId) {
        this.orderUserId = orderUserId;
    }


    @Column(name = "order_number", nullable = true)
    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }


    @Column(name = "order_amount", nullable = true, precision = 0)
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }


    @Column(name = "order_date", nullable = true)
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return orderId == orderItem.orderId &&
                Objects.equals(orderNo, orderItem.orderNo) &&
                Objects.equals(orderUserId, orderItem.orderUserId) &&
                Objects.equals(orderNumber, orderItem.orderNumber) &&
                Objects.equals(orderAmount, orderItem.orderAmount) &&
                Objects.equals(orderDate, orderItem.orderDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, orderNo, orderUserId, orderNumber, orderAmount, orderDate);
    }

    @OneToMany(mappedBy = "orderItem")
    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
