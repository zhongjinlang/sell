package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    String openid = "openid";
    @Autowired
    OrderService orderService;

    @Test
    public void findOne() {
        OrderDto orderDto = orderService.findOne("1582554052211213388");
        System.out.println(orderDto);
    }

    @Test
    public void findList() {
        Page<OrderDto> orderDtoPage = orderService.findList(openid, new PageRequest(0, 10));
        for (OrderDto orderDto : orderDtoPage.getContent()) {
            System.out.println(orderDto);
        }
    }

    @Test
    public void create() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("张三");
        orderDto.setBuyerPhone("12121211");
        orderDto.setBuyerAddress("zhangsan...");
        orderDto.setBuyerOpenid(openid);

        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("456");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);


        orderDto.setOrderDetailList(orderDetailList);
        log.info("购买的商品信息: {}", orderDto);
        orderService.create(orderDto);
    }

    @Test
    public void cancel() {
        OrderDto orderDto = orderService.findOne("1582554052211213388");
        orderService.cancel(orderDto);
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}