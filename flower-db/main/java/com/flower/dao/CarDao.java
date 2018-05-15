package com.flower.dao;

import com.flower.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by yumaoying on 2018/5/14.
 * 购物车
 */
public interface CarDao extends JpaRepository<Car, Integer>, JpaSpecificationExecutor<Car> {
    public Car save(Car car);

    @Query("update Car c set c.mount=?1 where c.carId=?2")
    public void updateMount(Integer mount, Integer carId);

    public void delete(Integer carId);
}

