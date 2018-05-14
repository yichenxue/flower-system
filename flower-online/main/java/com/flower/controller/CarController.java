package com.flower.controller;

import com.flower.entity.Car;
import com.flower.entity.Goods;
import com.flower.entity.User;
import com.flower.service.CarService;
import com.flower.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yumaoying on 2018/5/13.
 * 购物车
 */
@Controller
@RequestMapping({"/car"})
public class CarController {
    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CarService carService;

    //商品加入购物车
    @RequestMapping({"/addCar"})
    @ResponseBody
    public String addCar(Integer goodsId,
                         @RequestParam(required = false, defaultValue = "1") Integer mount,
                         HttpServletRequest request) {
        try {
            //用户购物车，商品id，购物车
            Map<Integer, Car> userCars = (Map<Integer, Car>) request.getSession().getAttribute("buyCars");
            if (userCars == null) {
                Car car = new Car();
                Goods goods = goodsService.findByGoodsId(goodsId);
                car.setGoods(goods);
                car.setMount(mount);
                userCars = new HashMap<>();
                userCars.put(goodsId, car);
                request.getSession().setAttribute("buyCars", userCars);
            } else {
                //如果购物车已经有该商品,对应商品数量加1
                if (userCars.get(goodsId) != null) {
                    Integer account = userCars.get(goodsId).getMount() + mount;
                    userCars.get(goodsId).setMount(account);
                    request.getSession().setAttribute("buyCars", userCars);
                } else {
                    //如果购物车没有该商品,将商品放入购物车
                    Car car = new Car();
                    Goods goods = goodsService.findByGoodsId(goodsId);
                    car.setGoods(goods);
                    car.setMount(mount);
                    userCars.put(goodsId, car);
                    request.getSession().setAttribute("buyCars", userCars);
                }
            }
            //设置购物车总量
            Integer carNumber = (Integer) request.getSession().getAttribute("carNumber");
            if (carNumber == null) {
                carNumber = 0;
            }
            request.getSession().setAttribute("carNumber", carNumber + mount);
            return "添加成功!";
        } catch (Exception e) {
            e.printStackTrace();
            return "添加失败!";
        }
    }


    //获取购物车shang'pi商品
    @RequestMapping({"/allCars"})
    public String getCars(HttpServletRequest request) {
        return "cars";
    }

    //改变购物车商品数量,设置购物车数量,返回购物车总量
    @RequestMapping({"/changeCar"})
    @ResponseBody
    public Integer changeCar(Integer goodsId, Integer mount, HttpServletRequest request) {
        Map<Integer, Car> userCars = (Map<Integer, Car>) request.getSession().getAttribute("buyCars");
        userCars.get(goodsId).setMount(mount);
        Integer carNumber = 0;
        //重现设置总量
        for (Car car : userCars.values()) {
            carNumber += car.getMount();
        }
        request.getSession().setAttribute("buyCars", userCars);
        request.getSession().setAttribute("carNumber", carNumber);
        return carNumber;
    }

    //删除购物车中的数据
    @RequestMapping({"/delCar"})
    public String delCar(Integer goodsId, HttpServletRequest request) {
        Map<Integer, Car> userCars = (Map<Integer, Car>) request.getSession().getAttribute("buyCars");
        Integer carNumber = (Integer) request.getSession().getAttribute("carNumber");
        Integer num = userCars.get(goodsId).getMount();
        carNumber = carNumber - num;
        userCars.remove(goodsId);
        request.getSession().setAttribute("buyCars", userCars);
        //减去移除商品的数量
        request.getSession().setAttribute("carNumber", carNumber);
        return "cars";
    }


    @RequestMapping({"/toJiesuan"})
    public String toJiesuan() {

        return "jiesuan";
    }
}
