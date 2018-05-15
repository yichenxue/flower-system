package com.flower.service;

import com.flower.entity.Car;
import com.flower.entity.User;

import java.util.Map;

/**
 * Created by yumaoying on 2018/5/13.
 */
public interface CarService {

    public Car save(Car car);

    //保存用户session中的购物车
    public void save(Map<Integer, Car> userCars);

    public void updateMount(Integer mount, Integer carId);

    public void delete(Integer carId);
}
