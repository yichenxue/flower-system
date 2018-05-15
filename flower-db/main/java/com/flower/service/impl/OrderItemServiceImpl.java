package com.flower.service.impl;

import com.flower.dao.OrderDetailDao;
import com.flower.dao.OrderItemDao;
import com.flower.dao.StockDao;
import com.flower.entity.OrderDetail;
import com.flower.entity.OrderItem;
import com.flower.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yumaoying on 2018/5/14.
 * 总订单
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemDao orderItemDao;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private StockDao stockDao;

    //使用事务，若出现错误，回退
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public OrderItem save(OrderItem orderItem) {
        OrderItem orderItem1 = orderItemDao.save(orderItem);
        for (OrderDetail orderDetail : orderItem.getOrderDetails()) {
            orderDetail.setOrderItem(orderItem1);
            orderDetailDao.save(orderDetail);
        }
        return orderItem;
    }

    ////根据系统生成的订单号查找
    public OrderItem findByOrderNo(String orderNo) {
        return orderItemDao.findByOrderNo(orderNo);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void orderPay(OrderItem orderItem, String orderPayWay) {
        //支付成功更新订单状态为03-支付成功
        orderDetailDao.updateOrderStatus("03", orderPayWay, orderItem.getOrderId());
        //查找订单编号下的所有子订单
        OrderItem orderItem1 = orderItemDao.findByOrderNo(orderItem.getOrderNo());
        //修改订单商品的库存量
        for (OrderDetail o : orderItem1.getOrderDetails()) {
            //查找原商品库存
            Integer oriNum = stockDao.findStockNumByGoods_GoodsId(o.getGoods().getGoodsId());
            //修改商品库存
            stockDao.updateStockNum(o.getGoods().getGoodsId(), oriNum - o.getOrderNumber());
        }
    }

    //删除总订单
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void deleteOrderItem(Integer orderItemId) {
        //先删除父订单下的子订单
        orderDetailDao.deleteParsentId(orderItemId);
        //再删除总订单信息
        orderItemDao.delete(orderItemId);

    }
}
