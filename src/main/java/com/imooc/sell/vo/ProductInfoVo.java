package com.imooc.sell.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInfoVo {
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    private String icon;
}
