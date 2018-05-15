package com.flower.service.impl;

import com.flower.dao.OrderDetailDao;
import com.flower.dao.StockDao;
import com.flower.entity.OrderDetail;
import com.flower.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yumaoying on 2018/5/14.
 */
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private StockDao stockDao;


    @Override
    public List<OrderDetail> findAll() {
        return orderDetailDao.findAll();
    }

    //按订单生成时间降序排
    @Override
    public List<OrderDetail> findAllOrderByOrderDateDesc() {
        return orderDetailDao.findAllOrderByOrderDateDesc();
    }

    public void delete(Integer id) {
        orderDetailDao.delete(id);
    }

    public OrderDetail findById(Integer id) {
        return orderDetailDao.findById(id);
    }

    public OrderDetail findByOrderNo(String orderNo) {
        return orderDetailDao.findByOrderNo(orderNo);
    }

    @Transactional
    public void updateOrderStatus(String status, String orderPayWay, Integer orderParentId) {
        orderDetailDao.updateOrderStatus(status, orderPayWay, orderParentId);
    }

    //子订单单独付款
    @Transactional
    public void orderDetailPay(OrderDetail orderDetail, String orderPayWay) {
        orderDetailDao.updateStatusAndOrderPayWay("03", orderPayWay, orderDetail.getId());
        OrderDetail orderDetail1 = orderDetailDao.findById(orderDetail.getId());
        //查找原商品库存
        Integer oriNum = stockDao.findStockNumByGoods_GoodsId(orderDetail1.getGoods().getGoodsId());
        //修改商品库存,减少
        stockDao.updateStockNum(orderDetail1.getGoods().getGoodsId(), oriNum - orderDetail1.getOrderNumber());
    }
}
