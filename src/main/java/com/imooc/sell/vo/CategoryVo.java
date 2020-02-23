package com.imooc.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imooc.sell.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVo {
    private String name;
    private Integer type;
    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVoList;
}
