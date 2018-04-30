package com.flower.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 */
@Entity
public class Goods {
    private Integer goodsId;
    private String goodsName;
    private String goodsDesc;
    private String goodsPic;
    private String goodsColor;
    private BigDecimal goodsShijia;
    private BigDecimal goodsPrice;
    private BigDecimal goodsPurchasePrice;
    private BigDecimal goodsDiscount;
    private String goodsMean;
    private List<Car> cars;
    private List<Comments> comments;
    private List<GoodsCategory> goodsCategories;
    private List<OrderDetail> orderDetailsByGoodsId;
    private List<Purchase> purchases;
    private List<Stock> stocks;

    @Id
    @Column(name = "goods_id", nullable = false)
    @GeneratedValue
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }


    @Column(name = "goods_name", nullable = true, length = 50)
    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }


    @Column(name = "goods_desc", nullable = true, length = 1000)
    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }


    @Column(name = "goods_pic", nullable = true, length = 50)
    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }


    @Column(name = "goods_color", nullable = true, length = 50)
    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }


    @Column(name = "goods_shijia", nullable = true, precision = 0)
    public BigDecimal getGoodsShijia() {
        return goodsShijia;
    }

    public void setGoodsShijia(BigDecimal goodsShijia) {
        this.goodsShijia = goodsShijia;
    }


    @Column(name = "goods_price", nullable = true, precision = 0)
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }


    @Column(name = "goods_purchase_price", nullable = true, precision = 0)
    public BigDecimal getGoodsPurchasePrice() {
        return goodsPurchasePrice;
    }

    public void setGoodsPurchasePrice(BigDecimal goodsPurchasePrice) {
        this.goodsPurchasePrice = goodsPurchasePrice;
    }


    @Column(name = "goods_discount", nullable = true, precision = 2)
    public BigDecimal getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(BigDecimal goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }


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
                Objects.equals(goodsPurchasePrice, goods.goodsPurchasePrice) &&
                Objects.equals(goodsDiscount, goods.goodsDiscount) &&
                Objects.equals(goodsMean, goods.goodsMean);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodsId, goodsName, goodsDesc, goodsPic, goodsColor, goodsShijia, goodsPrice, goodsPurchasePrice, goodsDiscount, goodsMean);
    }

    @OneToMany(mappedBy = "goods")
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @OneToMany(mappedBy = "goods")
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @OneToMany(mappedBy = "goods")
    public List<GoodsCategory> getGoodsCategories() {
        return goodsCategories;
    }

    public void setGoodsCategories(List<GoodsCategory> goodsCategories) {
        this.goodsCategories = goodsCategories;
    }

    @OneToMany(mappedBy = "goods")
    public List<OrderDetail> getOrderDetailsByGoodsId() {
        return orderDetailsByGoodsId;
    }

    public void setOrderDetailsByGoodsId(List<OrderDetail> orderDetailsByGoodsId) {
        this.orderDetailsByGoodsId = orderDetailsByGoodsId;
    }

    @OneToMany(mappedBy = "goods")
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    @OneToMany(mappedBy = "goods")
    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
