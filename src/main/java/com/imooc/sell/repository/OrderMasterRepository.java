package com.imooc.sell.repository;

import com.imooc.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    /**
     * 分页查询某个用户的订单
     */
    Page<OrderMaster> findByBuyerOpenid(String openid, Pageable pageable);
}
