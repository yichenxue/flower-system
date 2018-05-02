package com.flower.entity;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 */
@Entity
public class Goods implements Serializable {

    private static final long serialVersionUID = -8980079766278329448L;
    private Integer goodsId; //商品编号
    private String goodsName; //商品名称
    private String goodsDesc; //商品描述
    private String goodsPic; //商品图片
    private String goodsColor; //商品颜色
    private BigDecimal goodsShijia; //商品市场价
    private BigDecimal goodsPrice; //商品售价
    private BigDecimal goodsPurchasePrice; //进货单价
    private BigDecimal goodsDiscount; //商品折扣
    private String goodsMean; //花语
    private String goodsMaterial; //材质
    private String brand;//鲜花品牌
    private String pack; //包装
    private String remark; //说明
    private Stock stock;
    private List<Car> cars;
    private List<Comments> comments;
    private List<Category> categories;
    private List<OrderDetail> orderDetailsByGoodsId;
    private List<Purchase> purchases;

    @Id
    @Column(name = "goods_id", nullable = false)
    @GeneratedValue
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }


    @Column(name = "goods_name", nullable = false, length = 50)
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


    @Column(name = "goods_material", nullable = true, length = 50)
    public String getGoodsMaterial() {
        return goodsMaterial;
    }


    public void setGoodsMaterial(String goodsMaterial) {
        this.goodsMaterial = goodsMaterial;
    }

    @Column(name = "goods_rand", nullable = true, length = 50)
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Column(name = "goods_pack", nullable = true, length = 50)
    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    @Column(name = "goods_remark", nullable = true, length = 50)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
                Objects.equals(goodsMean, goods.goodsMean) &&
                Objects.equals(goodsMaterial, goods.goodsMaterial) &&
                Objects.equals(brand, goods.brand) &&
                Objects.equals(remark, goods.remark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsName, goodsDesc, goodsPic, goodsColor, goodsShijia, goodsPrice, goodsPurchasePrice, goodsDiscount, goodsMean, goodsMaterial, goodsMaterial, remark);
    }

    @JsonBackReference
    @OneToOne(mappedBy = "goods")
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GoodsCategory", joinColumns = {@JoinColumn(name = "goodsId")}, inverseJoinColumns = {@JoinColumn(name = "categoryId")})
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    public List<OrderDetail> getOrderDetailsByGoodsId() {
        return orderDetailsByGoodsId;
    }

    public void setOrderDetailsByGoodsId(List<OrderDetail> orderDetailsByGoodsId) {
        this.orderDetailsByGoodsId = orderDetailsByGoodsId;
    }

    @JsonBackReference
    @OneToMany(mappedBy = "goods", fetch = FetchType.LAZY)
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }


    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsDesc='" + goodsDesc + '\'' +
                ", goodsPic='" + goodsPic + '\'' +
                ", goodsColor='" + goodsColor + '\'' +
                ", goodsShijia=" + goodsShijia +
                ", goodsPrice=" + goodsPrice +
                ", goodsPurchasePrice=" + goodsPurchasePrice +
                ", goodsDiscount=" + goodsDiscount +
                ", goodsMean='" + goodsMean + '\'' +
                ", goodsMaterial='" + goodsMaterial + '\'' +
                ", brand='" + brand + '\'' +
                ", pack='" + pack + '\'' +
                ", remark='" + remark + '\'' +
                ", categories=" + categories +
                '}';
    }
}
