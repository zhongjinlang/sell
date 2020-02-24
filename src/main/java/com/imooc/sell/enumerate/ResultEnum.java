package com.imooc.sell.enumerate;

import lombok.Getter;

/**
 * 处理异常信息
 */
@Getter
public enum ResultEnum {

    PRODUCT_INFO_NO_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(20, "商品库存不正确")
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
