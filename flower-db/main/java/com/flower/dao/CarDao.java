package com.flower.dao;

import com.flower.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by yumaoying on 2018/5/14.
 * 购物车
 */
public interface CarDao extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {
    public Car save(Car car);

}

