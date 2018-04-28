package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/3/25.
 * 商品信息
 */
@Entity
public class Goods implements Serializable {
    private int goodsId;     //商品编号
    private String goodsName;  //商品名称
    private String goodsDesc;  //商品描述
    private String goodsPic;   //商品图片
    private String goodsColor;  //商品颜色
    private Double goodsShijia;  //商品市场价
    private Double goodsPrice;  //商品售价
    private Integer goodsCatelogId;  //商品类别id
    private Double goodsPurchasePrice;  //进货单价
    private BigDecimal goodsDiscount;  //商品折扣
    private String goodsMean;  //花语
    private List<Car> carList;
    private Category category;
    private List<OrderDetail> orderDetails;
    private List<Purchase> purchases;
    private List<Stock> stocks;

    @Id
    @Column(name = "goods_id", nullable = false)
    @GeneratedValue
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "goods_name", nullable = true, length = 50)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Basic
    @Column(name = "goods_desc", nullable = true, length = 1000)
    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    @Basic
    @Column(name = "goods_pic", nullable = true, length = 50)
    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    @Basic
    @Column(name = "goods_color", nullable = true, length = 50)
    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    @Basic
    @Column(name = "goods_shijia", nullable = true, precision = 0)
    public Double getGoodsShijia() {
        return goodsShijia;
    }

    public void setGoodsShijia(Double goodsShijia) {
        this.goodsShijia = goodsShijia;
    }

    @Basic
    @Column(name = "goods_price", nullable = true, precision = 0)
    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    @Basic
    @Column(name = "goods_catelog_id", nullable = true)
    public Integer getGoodsCatelogId() {
        return goodsCatelogId;
    }

    public void setGoodsCatelogId(Integer goodsCatelogId) {
        this.goodsCatelogId = goodsCatelogId;
    }

    @Basic
    @Column(name = "goods_purchase_price", nullable = true, precision = 0)
    public Double getGoodsPurchasePrice() {
        return goodsPurchasePrice;
    }

    public void setGoodsPurchasePrice(Double goodsPurchasePrice) {
        this.goodsPurchasePrice = goodsPurchasePrice;
    }

    @Basic
    @Column(name = "goods_discount", nullable = true, precision = 2)
    public BigDecimal getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(BigDecimal goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    @Basic
    @Column(name = "goods_mean", nullable = true, length = 1000)
    public String getGoodsMean() {
        return goodsMean;
    }

    public void setGoodsMean(String goodsMean) {
        this.goodsMean = goodsMean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return goodsId == goods.goodsId &&
                Objects.equals(goodsName, goods.goodsName) &&
                Objects.equals(goodsDesc, goods.goodsDesc) &&
                Objects.equals(goodsPic, goods.goodsPic) &&
                Objects.equals(goodsColor, goods.goodsColor) &&
                Objects.equals(goodsShijia, goods.goodsShijia) &&
                Objects.equals(goodsPrice, goods.goodsPrice) &&
                Objects.equals(goodsCatelogId, goods.goodsCatelogId) &&
                Objects.equals(goodsPurchasePrice, goods.goodsPurchasePrice) &&
                Objects.equals(goodsDiscount, goods.goodsDiscount) &&
                Objects.equals(goodsMean, goods.goodsMean);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodsId, goodsName, goodsDesc, goodsPic, goodsColor, goodsShijia, goodsPrice, goodsCatelogId, goodsPurchasePrice, goodsDiscount, goodsMean);
    }

    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    @ManyToOne
    @JoinColumn(name = "goods_catelog_id", referencedColumnName = "category_id")
    public Category getcategory() {
        return category;
    }

    public void setcategory(Category category) {
        this.category = category;
    }

    @OneToMany(mappedBy = "goods")
    public List<OrderDetail> getorderDetails() {
        return orderDetails;
    }

    public void setorderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @OneToMany(mappedBy = "goods")
    public List<Purchase> getpurchases() {
        return purchases;
    }

    public void setpurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @OneToMany(mappedBy = "goods")
    public List<Stock> getstocks() {
        return stocks;
    }

    public void setstocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
