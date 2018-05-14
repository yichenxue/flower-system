package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 * 订单明细
 */
@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = -6090122937654394880L;
    private Integer id; //编号
    private String orderNo; //订单编号
    private BigDecimal goodsPerPrice;//商品单价
    private Integer orderNumber;//订单数量
    private BigDecimal orderAmount;//订单金额
    private String orderPayWay;//付款方式
    private String orderDate;//订单生成日期
    private String orderStatus;//订单状态(00-未支付,02-支付失败,03-支付成功,04-已受理,待发货,05-已发货,运输中,07-待收货,08-已收货,09-待评价,10-已评价,11-订单已取消,12-订单已删除)
    private String orderAddress;//收货人地址
    private String orderUserName;//收货人姓名
    private String orderUserPhone;//收货人联系方式
    private String orderDeliver;//送货日期
    private String orderSenderName;//送货人姓名
    private String orderSenderTel;//送货人联系方式
    private String orderFinishDate;//订单完成日期(最后收货日期)
    private String orderRemark;//订单备注
    private String orderExpress;//快递公司
    private String orderExpressStatus;//物流状态
    private Goods goods; //商品id
    private User user;//用户id
    private OrderItem orderItem; //父订单id

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Column(name = "order_no", nullable = false, length = 32)
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    @Column(name = "goods_per_price", nullable = true, precision = 0)
    public BigDecimal getGoodsPerPrice() {
        return goodsPerPrice;
    }

    public void setGoodsPerPrice(BigDecimal goodsPerPrice) {
        this.goodsPerPrice = goodsPerPrice;
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


    @Column(name = "order_pay_way", nullable = true, length = 100)
    public String getOrderPayWay() {
        return orderPayWay;
    }

    public void setOrderPayWay(String orderPayWay) {
        this.orderPayWay = orderPayWay;
    }


    @Column(name = "order_date", nullable = true)
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


    @Column(name = "order_status", nullable = true, length = 4)
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


    @Column(name = "order_address", nullable = true, length = 150)
    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }


    @Column(name = "order_user_name", nullable = true, length = 100)
    public String getOrderUserName() {
        return orderUserName;
    }

    public void setOrderUserName(String orderUserName) {
        this.orderUserName = orderUserName;
    }


    @Column(name = "order_user_phone", nullable = true, length = 20)
    public String getOrderUserPhone() {
        return orderUserPhone;
    }

    public void setOrderUserPhone(String orderUserPhone) {
        this.orderUserPhone = orderUserPhone;
    }


    @Column(name = "order_deliver", nullable = true)
    public String getOrderDeliver() {
        return orderDeliver;
    }

    public void setOrderDeliver(String orderDeliver) {
        this.orderDeliver = orderDeliver;
    }


    @Column(name = "order_sender_name", nullable = true, length = 50)
    public String getOrderSenderName() {
        return orderSenderName;
    }

    public void setOrderSenderName(String orderSenderName) {
        this.orderSenderName = orderSenderName;
    }


    @Column(name = "order_sender_tel", nullable = true, length = 20)
    public String getOrderSenderTel() {
        return orderSenderTel;
    }

    public void setOrderSenderTel(String orderSenderTel) {
        this.orderSenderTel = orderSenderTel;
    }


    @Column(name = "order_finish_date", nullable = true)
    public String getOrderFinishDate() {
        return orderFinishDate;
    }

    public void setOrderFinishDate(String orderFinishDate) {
        this.orderFinishDate = orderFinishDate;
    }


    @Column(name = "order_remark", nullable = true, length = 500)
    public String getOrderRemark() {
        return orderRemark;
    }

    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }


    @Column(name = "order_express", nullable = true, length = 100)
    public String getOrderExpress() {
        return orderExpress;
    }

    public void setOrderExpress(String orderExpress) {
        this.orderExpress = orderExpress;
    }

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
        return id == that.id &&
                goods == that.goods &&
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
                Objects.equals(orderExpressStatus, that.orderExpressStatus);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, orderNo, goodsPerPrice, orderNumber, orderAmount, orderPayWay, orderDate, orderStatus, orderAddress, orderUserName, orderUserPhone, orderDeliver, orderSenderName, orderSenderTel, orderFinishDate, orderRemark, orderExpress, orderExpressStatus);
    }

    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "goods_id", nullable = false)
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @ManyToOne
    @JoinColumn(name = "order_user_id", referencedColumnName = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "order_parent_id", referencedColumnName = "order_id")
    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", goodsPerPrice=" + goodsPerPrice +
                ", orderNumber=" + orderNumber +
                ", orderAmount=" + orderAmount +
                ", orderPayWay='" + orderPayWay + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderUserName='" + orderUserName + '\'' +
                ", orderUserPhone='" + orderUserPhone + '\'' +
                ", orderDeliver='" + orderDeliver + '\'' +
                ", orderSenderName='" + orderSenderName + '\'' +
                ", orderSenderTel='" + orderSenderTel + '\'' +
                ", orderFinishDate='" + orderFinishDate + '\'' +
                ", orderRemark='" + orderRemark + '\'' +
                ", orderExpress='" + orderExpress + '\'' +
                ", orderExpressStatus='" + orderExpressStatus + '\'' +
                ", goods=" + goods +
                ", user=" + user +
                '}';
    }
}
