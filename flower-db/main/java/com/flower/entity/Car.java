package com.flower.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by yumaoying on 2018/4/29.
 * 购物车
 */
@Entity
public class Car implements Serializable {
    private static final long serialVersionUID = 5764432676551916335L;
    private Integer carId; //购物车编号
    private Integer mount; //商品数量
    private User user;//用户id
    private Goods goods;//商品id

    @Id
    @Column(name = "car_id", nullable = false)
    @GeneratedValue
    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }


    @Column(name = "mount", nullable = true)
    public Integer getMount() {
        return mount;
    }

    public void setMount(Integer mount) {
        this.mount = mount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId &&
                Objects.equals(mount, car.mount);
    }

    @Override
    public int hashCode() {

        return Objects.hash(carId, mount);
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "goods_id")
    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
