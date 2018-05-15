package com.flower.service.impl;

import com.flower.dao.CarDao;
import com.flower.entity.Car;
import com.flower.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by yumaoying on 2018/5/13.
 * 购物车
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Transactional
    public Car save(Car car) {
        return carDao.save(car);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void save(Map<Integer, Car> userCars) {
        for (Car sessionCar : userCars.values()) {
            carDao.save(sessionCar);
        }
    }

    public void updateMount(Integer mount, Integer carId) {
        carDao.updateMount(mount, carId);
    }

    @Transactional
    public void delete(Integer carId) {
        carDao.delete(carId);
    }
}
