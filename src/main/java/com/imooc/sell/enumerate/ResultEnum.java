package com.imooc.sell.enumerate;

import lombok.Getter;

/**
 * 处理异常信息
 */
@Getter
public enum ResultEnum {

    PRODUCT_INFO_NO_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(20, "商品库存不正确"),
    ORDER_NOT_EXIST(30,"订单不存在"),
    ORDER_DETAIL_NOT_EXIST(40,"订单详情不存在"),
    ORDER_STATUS_ERROR(50, "订单状态不正确"),
    ORDER_STATUS_UPDATE_ERROR(51, "订单状态更新失败"),
    ORDER_PAY_STATUS_ERROR(52,"订单支付状态不正确"),
    PARAMETER_ERROR(53,"参数错误");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
