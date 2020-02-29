package com.imooc.sell.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.enumerate.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.*;

/**
 * 买家订单相关接口
 */
@RestController
@RequestMapping("/buyer/order/")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultVo<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【创建订单】 参数错误, orderForm:{}", orderForm);
            throw new SellException(ResultEnum.PARAMETER_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = orderService.create(OrderFormToOrderDtoConvert(orderForm));
        Map<String, String> map = new HashMap<>();
        map.put("orderId", orderDto.getOrderId());
        return ResultVo.success(map);
    }

    /**
     * 将表单数据转换为OrderDto
     * @param orderForm
     * @return
     */
    private OrderDto OrderFormToOrderDtoConvert(OrderForm orderForm){
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        // 将前台购物车string转换为list
        try {
            List<OrderDetail> orderDetailList = JSON.parseObject(orderForm.getItems(), new TypeReference<List<OrderDetail>>(){});
            orderDto.setOrderDetailList(orderDetailList);
        }catch (Exception e){
            log.error("OrderFormToOrderDtoConvert Error");
            throw new SellException(ResultEnum.PARAMETER_ERROR);
        }
        return orderDto;
    }
}

