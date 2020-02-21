package com.imooc.sell.enumerate;

import lombok.Getter;

/**
 * 商品状态
 */

@Getter
public enum ProductStatusEnum {

    UP(0, "商品在架"),
    DOWN(1,"商品已下架")


    ;
    private Integer code;
    private String desc;

    ProductStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
