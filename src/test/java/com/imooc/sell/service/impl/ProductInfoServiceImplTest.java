package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoService productInfoService;


    @Test
    public void findOne() {
        System.out.println(productInfoService.findOne("123"));
    }

    @Test
    public void findAll() {
        PageRequest pageRequest = new PageRequest(0, 10);
        Page<ProductInfo> list = productInfoService.findAll(pageRequest);
        List<ProductInfo> content = list.getContent();
        for (ProductInfo productInfo : content) {
            System.out.println(productInfo);
        }
    }

    @Test
    public void findProductUp() {
        List<ProductInfo> productUp = productInfoService.findProductUp();
        for (ProductInfo productInfo : productUp) {
            System.out.println(productInfo);
        }
    }

    @Test
    public void save() {
    }
}