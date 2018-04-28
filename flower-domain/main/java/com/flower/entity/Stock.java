package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/3/25.
 * 库存信息
 */
@Entity
public class Stock implements Serializable {
    private int id; //编号
    private int goodsId; //商品编号
    private Integer stockNum; //库存数量
    private Boolean alarm; //是否已报警(0-未报警,1-已报警)
    private String desc; //备注信息
    private Goods goods;

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
    @Column(name = "goods_id", nullable = false)
    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    @Basic
    @Column(name = "stock_num", nullable = true)
    public Integer getStockNum() {
        return stockNum;
    }

    public void setStockNum(Integer stockNum) {
        this.stockNum = stockNum;
    }

    @Basic
    @Column(name = "alarm", nullable = true)
    public Boolean getAlarm() {
        return alarm;
    }

    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    @Basic
    @Column(name = "desc", nullable = true, length = 100)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return id == stock.id &&
                goodsId == stock.goodsId &&
                Objects.equals(stockNum, stock.stockNum) &&
                Objects.equals(alarm, stock.alarm) &&
                Objects.equals(desc, stock.desc);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, goodsId, stockNum, alarm, desc);
    }

    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "goods_id", nullable = false)
    public Goods getgoods() {
        return goods;
    }

    public void setgoods(Goods goods) {
        this.goods = goods;
    }
}
