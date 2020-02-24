package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enumerate.OrderStatusEnum;
import com.imooc.sell.enumerate.PayStatusEnum;
import com.imooc.sell.enumerate.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.utils.KeyUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    /**
     * 查询订单详情, 订单主表以及订单详情列表
     * @param orderId
     * @return
     */
    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) throw new SellException(ResultEnum.ORDER_NOT_EXIST);

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) throw new SellException(ResultEnum.ORDER_DETAIL_NOT_EXIST);

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    /**
     * 订单列表, 不需要订单详情数据
     * @param openid
     * @param pageable
     * @return
     */
    @Override
    public Page<OrderDto> findList(String openid, Pageable pageable) {
        Page<OrderMaster> OrderMasterPage = orderMasterRepository.findByBuyerOpenid(openid, pageable);
        List<OrderMaster> orderMasterList = OrderMasterPage.getContent();
        // 将orderMaster转换为orderDto
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderMaster orderMaster : orderMasterList) {
            OrderDto orderDto = new OrderDto();
            BeanUtils.copyProperties(orderMaster,orderDto);
            orderDtoList.add(orderDto);
        }
        Page<OrderDto> orderDtoPage = new PageImpl<>(orderDtoList, pageable, OrderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    /**
     * 创建订单
     *
     * @param orderDto
     * @return
     */
    @Override
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtils.genUniqueKey();
        BigDecimal total = new BigDecimal(BigInteger.ZERO);
        // 遍历购物车中的商品信息，从数据库中查询商品价格
        for (OrderDetail orderDetail : orderDto.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) throw new SellException(ResultEnum.PRODUCT_INFO_NO_EXIST);
            // 计算总价
            total = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(total);

            // 订单detail入库
            orderDetail.setDetailId(KeyUtils.genUniqueKey()); // 主键
            orderDetail.setOrderId(orderId); // 订单id
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        // 订单master入库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(total);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        // 减少库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(e ->
                new CartDto(e.getProductId(), e.getProductQuantity())
        ).collect(Collectors.toList());
        productInfoService.decrease(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(Order order) {
        return null;
    }
}
