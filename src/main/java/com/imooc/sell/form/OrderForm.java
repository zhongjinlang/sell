package com.imooc.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 创建订单表单数据
 */
@Data
public class OrderForm {

    @NotEmpty(message = "买家名称必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "买家openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
