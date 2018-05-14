package com.flower.controller;

import com.flower.entity.*;
import com.flower.service.OrderDetailService;
import com.flower.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yumaoying on 2018/5/14.
 */
@Controller
@RequestMapping({"/order"})
public class OrderController {
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private OrderDetailService orderDetailService;

    //购物车商品生成订单
    @RequestMapping({"/addOrder"})
    public String addOrder(@RequestParam("goodsIds") String goodsIds, OrderDetail order,
                           Model model,
                           HttpServletRequest request) {
        System.out.println("order===" + order);
        //若未选中商品,转到购物车页面
        if (goodsIds == null || goodsIds.isEmpty()) {
            return "cars";
        }
        User loginuser = (User) request.getSession().getAttribute("loginUser");
        if (loginuser == null) {
            //若用户为登陆，先提示登陆
            //转发到登陆页面
            return "redirect:/toLogin";
        } else {
            Map<Integer, Car> userCars = (Map<Integer, Car>) request.getSession().getAttribute("buyCars");
            String[] gs = goodsIds.split(",");
            //保存
            List<OrderDetail> orderDetails = new ArrayList<>();
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String orderDate = time.format(new Date());
            //初始化订单数量
            Integer orderNumber = 0;
            //初始化订单总额
            BigDecimal orderAmount = new BigDecimal(0.00);
            for (int i = 0; i < gs.length; i++) {
                Integer goodsId = new Integer(gs[i]);
                Goods goods = userCars.get(goodsId).getGoods();
                orderNumber += userCars.get(goodsId).getMount();
                //计算单笔金额，采用精度高的BigDecimal进行转换
                BigDecimal mount = new BigDecimal(userCars.get(goodsId).getMount() + "");
                BigDecimal perPrice = goods.getGoodsPrice().multiply(mount);
                orderAmount = orderAmount.add(perPrice);
                //设置订单明细
                OrderDetail orderDetail = new OrderDetail();
                //设置订单编号
                orderDetail.setOrderNo(UUID.randomUUID().toString().replace("-", ""));
                //设置商品下单时的单笔价格
                orderDetail.setGoodsPerPrice(goods.getGoodsPrice());
                //设置单笔订单的下单数量
                orderDetail.setOrderNumber(userCars.get(goodsId).getMount());
                //设置单笔订单价格
                orderDetail.setOrderAmount(perPrice);
                //设置订单生成时间
                orderDetail.setOrderDate(orderDate);
                //设置订单状态为未支付
                orderDetail.setOrderStatus("00");
                //设置用户
                orderDetail.setUser(loginuser);
                //设置商品
                orderDetail.setGoods(goods);
                //设置收货人姓名
                orderDetail.setOrderUserName(order.getOrderUserName());
                //设置收货人联系方式
                orderDetail.setOrderUserPhone(order.getOrderUserPhone());
                //收货人地址
                orderDetail.setOrderAddress(order.getOrderAddress());
                orderDetails.add(orderDetail);
            }
            //设置总订单
            OrderItem orderItem = new OrderItem();
            String orderNo = UUID.randomUUID().toString().replace("-", "");
            orderItem.setOrderNo(orderNo);
            orderItem.setOrderUserId(loginuser.getUserId());
            //设置订单总量
            orderItem.setOrderNumber(orderNumber);
            //设置订单总额
            orderItem.setOrderAmount(orderAmount);
            //设置下单时间
            orderItem.setOrderDate(orderDate);
            orderItem.setOrderDetails(orderDetails);
            //保存订单
            OrderItem saveOrderItem = orderItemService.save(orderItem);
            model.addAttribute("orderItem", saveOrderItem);
            return "jiesuan";
        }
    }

    //查找全部订单
    @RequestMapping({"/findAll"})
    public String findAll(Model model, HttpServletRequest request) {
        User loginuser = (User) request.getSession().getAttribute("loginUser");
        if (loginuser == null) {
            //若用户为登陆，先提示登陆
            //转发到登陆页面
            return "redirect:/toLogin";
        }
        List<OrderDetail> orderDetails = orderDetailService.findAll();
        model.addAttribute("orderDetails", orderDetails);
        return "order";
    }

    //删除订单
    @RequestMapping({"/delete"})
    public String delete(Integer id, Model model) {
        orderDetailService.delete(id);
        model.addAttribute("msg", "删除成功!");
        return "redirect:findAll";
    }

    //根据订单编号查找订单
    @RequestMapping({"/find"})
    public String find(String orderNo, Model model) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = orderDetailService.findByOrderNo(orderNo);
        if (orderDetail != null) {
            orderDetails.add(orderDetail);
        }
        model.addAttribute("orderDetails", orderDetails);
        return "order";
    }

    //订单详情
    @RequestMapping({"/findById"})
    public String findById(Integer id, Model model) {
        OrderDetail orderDetail = orderDetailService.findById(id);
        model.addAttribute("order", orderDetail);
        return "orderDetail";
    }
}
