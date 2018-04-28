package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/3/25.
 * 订单明细
 */
@Entity
@Table(name = "order_detail", schema = "hua", catalog = "")
public class OrderDetail implements Serializable {
    private int orderId;  //编号
    private String orderNo; //订单编号
    private int goodsId; //商品id
    private Double goodsPerPrice; //商品单价
    private Integer orderNumber; //订单数量
    private Double orderAmount; //订单金额
    private int orderUserId; //订单用户id
    private String orderPayWay; //付款方式
    private Timestamp orderDate; //订单生成日期
    private String orderStatus; //订单状态(00-未支付,02-支付失败,03-支付成功,04-已受理,待发货,05-已发货,运输中,07-待收货,08-已收货,09-待评价,10-已评价,11-订单已取消,12-订单已删除)
    private String orderAddress; //收款人地址
    private String orderUserName; //收货人姓名
    private String orderUserPhone; //收货人联系方式
    private Timestamp orderDeliver; //送货日期
    private String orderSenderName; //送货人姓名
    private String orderSenderTel; //送货人联系方式
    private Timestamp orderFinishDate; //订单完成日期(最后收货日期)
    private String orderRemark; //订单备注
    private String orderExpress; //快递公司
    private Integer orderParentId; //父订单id
    private String orderExpressStatus; //物流状态
    private Goods goods; //
    private User user; //
    private Order order; //

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
    @Column(name = "order_no", nullable = false, length = 32)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Basic
    @Column(name = "goods_id", nullable = false)
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "goods_per_price", nullable = true, precision = 0)
    public Double getGoodsPerPrice() {
        return goodsPerPrice;
    }

    public void setGoodsPerPrice(Double goodsPerPrice) {
        this.goodsPerPrice = goodsPerPrice;
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
    @Column(name = "order_user_id", nullable = false)
    public int getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(int orderUserId) {
        this.orderUserId = orderUserId;
    }

    @Basic
    @Column(name = "order_pay_way", nullable = true, length = 100)
    public String getOrderPayWay() {
        return orderPayWay;
    }

    public void setOrderPayWay(String orderPayWay) {
        this.orderPayWay = orderPayWay;
    }

    @Basic
    @Column(name = "order_date", nullable = true)
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "order_status", nullable = true, length = 4)
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "order_address", nullable = true, length = 150)
    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    @Basic
    @Column(name = "order_user_name", nullable = true, length = 100)
    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }

    @Basic
    @Column(name = "order_user_phone", nullable = true, length = 20)
    public String getOrderUserPhone() {
        return orderUserPhone;
    }

    public void setOrderUserPhone(String orderUserPhone) {
        this.orderUserPhone = orderUserPhone;
    }

    @Basic
    @Column(name = "order_deliver", nullable = true)
    public Timestamp getOrderDeliver() {
        return orderDeliver;
    }

    public void setOrderDeliver(Timestamp orderDeliver) {
        this.orderDeliver = orderDeliver;
    }

    @Basic
    @Column(name = "order_sender_name", nullable = true, length = 50)
    public String getOrderSenderName() {
        return orderSenderName;
    }

    public void setOrderSenderName(String orderSenderName) {
        this.orderSenderName = orderSenderName;
    }

    @Basic
    @Column(name = "order_sender_tel", nullable = true, length = 20)
    public String getOrderSenderTel() {
        return orderSenderTel;
    }

    public void setOrderSenderTel(String orderSenderTel) {
        this.orderSenderTel = orderSenderTel;
    }

    @Basic
    @Column(name = "order_finish_date", nullable = true)
    public Timestamp getOrderFinishDate() {
        return orderFinishDate;
    }

    public void setOrderFinishDate(Timestamp orderFinishDate) {
        this.orderFinishDate = orderFinishDate;
    }

    @Basic
    @Column(name = "order_remark", nullable = true, length = 500)
    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    @Basic
    @Column(name = "order_express", nullable = true, length = 100)
    public String getOrderExpress() {
        return orderExpress;
    }

    public void setOrderExpress(String orderExpress) {
        this.orderExpress = orderExpress;
    }

    @Basic
    @Column(name = "order_parent_id", nullable = true)
    public Integer getOrderParentId() {
        return orderParentId;
    }

    public void setOrderParentId(Integer orderParentId) {
        this.orderParentId = orderParentId;
    }

    @Basic
    @Column(name = "order_express_status", nullable = true, length = 100)
    public String getOrderExpressStatus() {
        return orderExpressStatus;
    }

    public void setOrderExpressStatus(String orderExpressStatus) {
        this.orderExpressStatus = orderExpressStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return orderId == that.orderId &&
                goodsId == that.goodsId &&
                orderUserId == that.orderUserId &&
                Objects.equals(orderNo, that.orderNo) &&
                Objects.equals(goodsPerPrice, that.goodsPerPrice) &&
                Objects.equals(orderNumber, that.orderNumber) &&
                Objects.equals(orderAmount, that.orderAmount) &&
                Objects.equals(orderPayWay, that.orderPayWay) &&
                Objects.equals(orderDate, that.orderDate) &&
                Objects.equals(orderStatus, that.orderStatus) &&
                Objects.equals(orderAddress, that.orderAddress) &&
                Objects.equals(orderUserName, that.orderUserName) &&
                Objects.equals(orderUserPhone, that.orderUserPhone) &&
                Objects.equals(orderDeliver, that.orderDeliver) &&
                Objects.equals(orderSenderName, that.orderSenderName) &&
                Objects.equals(orderSenderTel, that.orderSenderTel) &&
                Objects.equals(orderFinishDate, that.orderFinishDate) &&
                Objects.equals(orderRemark, that.orderRemark) &&
                Objects.equals(orderExpress, that.orderExpress) &&
                Objects.equals(orderParentId, that.orderParentId) &&
                Objects.equals(orderExpressStatus, that.orderExpressStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, orderNo, goodsId, goodsPerPrice, orderNumber, orderAmount, orderUserId, orderPayWay, orderDate, orderStatus, orderAddress, orderUserName, orderUserPhone, orderDeliver, orderSenderName, orderSenderTel, orderFinishDate, orderRemark, orderExpress, orderParentId, orderExpressStatus);
    }

    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "goods_id", nullable = false)
    public Goods getgoods() {
        return goods;
    }

    public void setgoods(Goods goods) {
        this.goods = goods;
    }

    @ManyToOne
    @JoinColumn(name = "order_user_id", referencedColumnName = "user_id", nullable = false)
    public User getuser() {
        return user;
    }

    public void setuser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "order_parent_id", referencedColumnName = "order_id")
    public Order getorder() {
        return order;
    }

    public void setorder(Order order) {
        this.order = order;
    }
}
