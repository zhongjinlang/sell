package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDto;
import org.hibernate.criterion.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    /**
     * 查询单个订单
     */
    OrderDto findOne(String orderId);

    /**
     * 查询某个用户的订单列表
     */
    Page<OrderDto> findList(String openid, Pageable pageable);

    /**
     * 创建订单
     */
    OrderDto create(OrderDto orderDto);

    /**
     * 取消订单
     */
    OrderDto cancel(OrderDto orderDto);

    /**
     * 完结订单
     */
    OrderDto finish(OrderDto orderDto);

    /**
     * 支付订单
     */
    OrderDto paid(Order order);

}
