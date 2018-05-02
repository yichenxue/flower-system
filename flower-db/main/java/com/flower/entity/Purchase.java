package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 * 采购信息
 */
@Entity
public class Purchase implements Serializable {
    private static final long serialVersionUID = 5053185158532884009L;
    private Integer id; //编号
    private String purchaseDate; //采购日期
    private Integer purchaseNumber;
    private Double purchasePrice;
    private String purchaseUser;
    private String purchaseUserTel;
    private Goods goods;
    private Supplier supplier;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "pruchase_date", nullable = true)
    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }


    @Column(name = "purchase_number", nullable = true)
    public Integer getPurchaseNumber() {
        return purchaseNumber;
    }

    public void setPurchaseNumber(Integer purchaseNumber) {
        this.purchaseNumber = purchaseNumber;
    }


    @Column(name = "purchase_price", nullable = true, precision = 0)
    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }


    @Column(name = "purchase_user", nullable = true, length = 50)
    public String getPurchaseUser() {
        return purchaseUser;
    }

    public void setPurchaseUser(String purchaseUser) {
        this.purchaseUser = purchaseUser;
    }

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
                Objects.equals(purchaseDate, purchase.purchaseDate) &&
                Objects.equals(purchaseNumber, purchase.purchaseNumber) &&
                Objects.equals(purchasePrice, purchase.purchasePrice) &&
                Objects.equals(purchaseUser, purchase.purchaseUser) &&
                Objects.equals(purchaseUserTel, purchase.purchaseUserTel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, purchaseDate, purchaseNumber, purchasePrice, purchaseUser, purchaseUserTel);
    }

    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "goods_id")
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @ManyToOne
    @JoinColumn(name = "purchase_sup_id", referencedColumnName = "sup_id")
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", purchaseDate='" + purchaseDate + '\'' +
                ", purchaseNumber=" + purchaseNumber +
                ", purchasePrice=" + purchasePrice +
                ", purchaseUser='" + purchaseUser + '\'' +
                ", purchaseUserTel='" + purchaseUserTel + '\'' +
                ", goods=" + goods +
                ", supplier=" + supplier +
                '}';
    }
}
