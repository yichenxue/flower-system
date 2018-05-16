package com.flower.controller;

import com.flower.entity.OrderDetail;
import com.flower.entity.OrderItem;
import com.flower.service.OrderDetailService;
import com.flower.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yumaoying on 2018/5/16.
 */
@Controller
@RequestMapping("/goods")
public class OrderController {
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderDetailService orderDetailService;


}
