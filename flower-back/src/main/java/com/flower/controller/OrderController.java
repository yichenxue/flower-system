package com.flower.controller;

import com.flower.entity.OrderDetail;
import com.flower.entity.OrderItem;
import com.flower.service.OrderDetailService;
import com.flower.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yumaoying on 2018/5/16.
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderDetailService orderDetailService;

    @RequestMapping
    public String orderPage() {
        return "order/orders";
    }

    @RequestMapping("/orderItemPage")
    public String orderItemPage() {
        return "order/ordersItems";
    }

    @RequestMapping("/orders")
    @ResponseBody
    public Map<String, Object> get(OrderDetail orderDetail, String draw,
                                   @RequestParam(required = false, defaultValue = "0") int start,
                                   @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = orderDetailService.findAll(orderDetail, pageable);
        map.put("draw", draw);
        map.put("recordsTotal", page.getTotalElements());
        map.put("data", page.getContent());
        map.put("recordsFiltered", page.getTotalElements());
        return map;
    }

    //查看总订单
    @RequestMapping("/orderItems")
    @ResponseBody
    public Map<String, Object> getOrderItems(OrderItem orderDetail, String draw,
                                             @RequestParam(required = false, defaultValue = "0") int start,
                                             @RequestParam(required = false, defaultValue = "10") int length) {
        Map<String, Object> map = new HashMap<>();
        Sort sort = new Sort(Sort.Direction.ASC, "orderId");
        Pageable pageable = new PageRequest(start / length, length, sort);
        Page page = orderItemService.findAll(orderDetail, pageable);
        map.put("recordsTotal", page.getTotalElements());
        map.put("draw", draw);
        map.put("recordsFiltered", page.getTotalElements());
        map.put("data", page.getContent());
        return map;
    }


    //子订单详情
    @RequestMapping({"/findById"})
    public String findById(Integer id, Model model) {
        OrderDetail orderDetail = orderDetailService.findById(id);
        model.addAttribute("order", orderDetail);
        return "order/orderDetail";
    }

    //子订单修改订单状态
    @RequestMapping({"/updateOrderStatus"})
    @ResponseBody
    public String updateOrderStatus(Integer id, String orderStatus, Model model) {
        System.out.println("================" + id);
        try {
            orderDetailService.updateOrderStatus(id, orderStatus);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //订单发货
    @RequestMapping({"/orderSend"})
    @ResponseBody
    public String orderSend(OrderDetail orderDetail, Model model) {
        try {
            OrderDetail orderDetail2 = orderDetailService.findById(orderDetail.getId());
            //设置快递公司
            orderDetail2.setOrderExpress(orderDetail.getOrderExpress());
            //设置快递员
            orderDetail2.setOrderSenderName(orderDetail.getOrderSenderName());
            //设置快递员联系方式
            orderDetail2.setOrderSenderTel(orderDetail.getOrderSenderTel());
            //设置为已发货
            orderDetail2.setOrderStatus("06");
            //设置物流状态
            orderDetail2.setOrderExpressStatus(orderDetail.getOrderExpressStatus());
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String orderDate = time.format(new Date());
            //设置送货日期
            orderDetail2.setOrderDeliver(orderDate);
            orderDetailService.save(orderDetail2);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //删除订单
    @RequestMapping({"/delete"})
    @ResponseBody
    public String delete(Integer id) {
        try {
            orderDetailService.delete(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "删除失败";
        }
    }


}
