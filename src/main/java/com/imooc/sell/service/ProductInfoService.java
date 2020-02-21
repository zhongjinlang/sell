package com.imooc.sell.service;

import com.imooc.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 查询所有已上架商品
     */
    List<ProductInfo> findProductUp();

    ProductInfo save(ProductInfo productInfo);
}
