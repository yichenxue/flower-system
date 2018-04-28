package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/3/25.
 * 采购信息
 */
@Entity
public class Purchase implements Serializable {
    private int id;  //编号
    private Integer goodsId;  //商品编号
    private Timestamp pruchaseDate; //采购日期
    private Integer purchaseNumber; //采购数量
    private Double purchasePrice; //采购单价
    private Integer purchaseSupId; //供应商id
    private String purchaseUser; //采购人员姓名
    private String purchaseUserTel;  //采购人员联系方式
    private Goods goods;
    private Supplier supplier;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "goods_id", nullable = true)
    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "pruchase_date", nullable = true)
    public Timestamp getPruchaseDate() {
        return pruchaseDate;
    }

    public void setPruchaseDate(Timestamp pruchaseDate) {
        this.pruchaseDate = pruchaseDate;
    }

    @Basic
    @Column(name = "purchase_number", nullable = true)
    public Integer getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(Integer purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }

    @Basic
    @Column(name = "purchase_price", nullable = true, precision = 0)
    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    @Basic
    @Column(name = "purchase_sup_id", nullable = true)
    public Integer getPurchaseSupId() {
        return purchaseSupId;
    }

    public void setPurchaseSupId(Integer purchaseSupId) {
        this.purchaseSupId = purchaseSupId;
    }

    @Basic
    @Column(name = "purchase_user", nullable = true, length = 50)
    public String getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(String purchaseUser) {
        this.purchaseUser = purchaseUser;
    }

    @Basic
    @Column(name = "purchase_user_tel", nullable = true, length = 20)
    public String getPurchaseUserTel() {
        return purchaseUserTel;
    }

    public void setPurchaseUserTel(String purchaseUserTel) {
        this.purchaseUserTel = purchaseUserTel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchase purchase = (Purchase) o;
        return id == purchase.id &&
                Objects.equals(goodsId, purchase.goodsId) &&
                Objects.equals(pruchaseDate, purchase.pruchaseDate) &&
                Objects.equals(purchaseNumber, purchase.purchaseNumber) &&
                Objects.equals(purchasePrice, purchase.purchasePrice) &&
                Objects.equals(purchaseSupId, purchase.purchaseSupId) &&
                Objects.equals(purchaseUser, purchase.purchaseUser) &&
                Objects.equals(purchaseUserTel, purchase.purchaseUserTel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, goodsId, pruchaseDate, purchaseNumber, purchasePrice, purchaseSupId, purchaseUser, purchaseUserTel);
    }

    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "goods_id")
    public Goods getgoods() {
        return goods;
    }

    public void setgoods(Goods goods) {
        this.goods = goods;
    }

    @ManyToOne
    @JoinColumn(name = "purchase_sup_id", referencedColumnName = "sup_id")
    public Supplier getsupplier() {
        return supplier;
    }

    public void setsupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
