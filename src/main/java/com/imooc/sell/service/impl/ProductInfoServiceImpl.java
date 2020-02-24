package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.enumerate.ProductStatusEnum;
import com.imooc.sell.enumerate.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repositorye;

    @Override
    public ProductInfo findOne(String productId) {
        return repositorye.findOne(productId);
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repositorye.findAll(pageable);
    }

    @Override
    public List<ProductInfo> findProductUp() {
        return repositorye.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repositorye.save(productInfo);
    }

    @Override
    @Transactional
    public void decrease(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            // 根据商品id查询商品
            ProductInfo productInfo = repositorye.findOne(cartDto.getProductId());
            if (productInfo == null) throw new SellException(ResultEnum.PRODUCT_INFO_NO_EXIST);
            Integer stock = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (stock < 0) throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            productInfo.setProductStock(stock);
            repositorye.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void increase(List<CartDto> cartDtoList) {
        for (CartDto cartDto : cartDtoList) {
            // 根据商品id查询商品
            ProductInfo productInfo = repositorye.findOne(cartDto.getProductId());
            if (productInfo == null) throw new SellException(ResultEnum.PRODUCT_INFO_NO_EXIST);
            Integer stock = productInfo.getProductStock() + cartDto.getProductQuantity();
            productInfo.setProductStock(stock);
            repositorye.save(productInfo);
        }
    }
}
