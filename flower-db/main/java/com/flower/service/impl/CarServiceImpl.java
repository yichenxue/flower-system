package com.flower.service.impl;

import com.flower.dao.CarDao;
import com.flower.entity.Car;
import com.flower.service.CarService;
import org.springframework.stereotype.Service;

/**
 * Created by yumaoying on 2018/5/13.
 * 购物车
 */
@Service
public class CarServiceImpl implements CarService {
    private CarDao carDao;

    public Car save(Car car) {
        return carDao.save(car);
    }
}
