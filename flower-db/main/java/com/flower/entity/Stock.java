package com.flower.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 */
@Entity
public class Stock {
    private Integer id;
    private Integer stockNum;
    private Integer isAlarm;
    private String descri;
    private Goods goods;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Column(name = "stock_num", nullable = true)
    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    @Column(name = "is_alarm", nullable = true)
    public Integer getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(Integer isAlarm) {
        this.isAlarm = isAlarm;
    }


    @Column(name = "descri", nullable = true, length = 100)
    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return id == stock.id &&
                Objects.equals(stockNum, stock.stockNum) &&
                Objects.equals(isAlarm, stock.isAlarm) &&
                Objects.equals(descri, stock.descri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, stockNum, isAlarm, descri);
    }

    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "goods_id", nullable = false)
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
