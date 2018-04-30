package com.flower.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 */
@Entity
public class Purchase {
    private Integer id;
    private String pruchaseDate;
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
    public String getPruchaseDate() {
        return pruchaseDate;
    }

    public void setPruchaseDate(String pruchaseDate) {
        this.pruchaseDate = pruchaseDate;
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
                Objects.equals(pruchaseDate, purchase.pruchaseDate) &&
                Objects.equals(purchaseNumber, purchase.purchaseNumber) &&
                Objects.equals(purchasePrice, purchase.purchasePrice) &&
                Objects.equals(purchaseUser, purchase.purchaseUser) &&
                Objects.equals(purchaseUserTel, purchase.purchaseUserTel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, pruchaseDate, purchaseNumber, purchasePrice, purchaseUser, purchaseUserTel);
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
}
