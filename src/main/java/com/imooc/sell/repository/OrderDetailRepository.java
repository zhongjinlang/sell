package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    /**
     * 从订单主表的订单id查询出订单详情信息
     */
    Page<OrderDetail> findByOrderId(String orderId, Pageable pageable);

    List<OrderDetail> findByOrderId(String orderId);
}
