package com.springcloud.service.impl;

import com.springcloud.dao.OrderDao;
import com.springcloud.entities.Order;
import com.springcloud.service.AccountService;
import com.springcloud.service.OrderService;
import com.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private StorageService storageService;
    @Autowired
    private AccountService accountService;

    @Override
    public void create(Order order) {
        log.info("----->开始新建订单");
        orderDao.create(order);
        log.info("----->微服务开始调用库存修改");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("----->微服务开始调用余额修改");
        accountService.decrease(order.getUserId(),order.getMoney());

        //修改订单状态
        log.info("----->开始修改订单状态");
        orderDao.update(order.getUserId(),0);
        log.info("----->成功下订单");
    }
}
