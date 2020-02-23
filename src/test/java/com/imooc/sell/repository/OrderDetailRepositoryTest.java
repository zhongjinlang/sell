package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderDetail;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest{

    @Autowired
    OrderDetailRepository repository;

    @Test
    public void findByOrderId() {
        Page<OrderDetail> page = repository.findByOrderId("123456", new PageRequest(0, 10));
        List<OrderDetail> content = page.getContent();
        System.out.println(content);
    }
}