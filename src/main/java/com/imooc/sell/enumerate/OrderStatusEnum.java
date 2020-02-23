package com.imooc.sell.enumerate;

import lombok.Getter;

import javax.annotation.Generated;

/**
 * 订单状态
 */
@Getter
public enum  OrderStatusEnum {

    NEW(0, "新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消"),

    ;
    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
