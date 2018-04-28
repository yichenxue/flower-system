package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by yumaoying on 2018/3/25.
 * 购物车
 */
@Entity
public class Car implements Serializable {
    private int carId;
    private Integer userId; //用户id
    private Integer goodsId; //商品id
    private Integer mount;//商品数量

    private User user;//用户
    private List<Goods> goodsList;//商品

    @Id
    @Column(name = "car_id", nullable = false)
    @GeneratedValue
    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    //@Basic用于指定加载方式，默认为即时加载@Basic(fetch = FetchType.EAGER),
    // 对象实例化时，即加载了实体中的对应属性
    // @Basic(fetch = FetchType.LAZY)-懒加载
    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
    @Column(name = "mount", nullable = true)
    public Integer getMount() {
        return mount;
    }

    public void setMount(Integer mount) {
        this.mount = mount;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany()
    @JoinColumn(name = "goods_id", referencedColumnName = "goods_id")
    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
