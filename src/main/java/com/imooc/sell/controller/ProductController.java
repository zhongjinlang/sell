package com.imooc.sell.controller;

import ch.qos.logback.core.util.COWArrayList;
import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.service.CategoryService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.vo.CategoryVo;
import com.imooc.sell.vo.ProductInfoVo;
import com.imooc.sell.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private CategoryService categoryService;
    /**
     * 已上架商品列表
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResultVo productUpList(){
        // 已上架商品信息
        List<ProductInfo> productUp = productInfoService.findProductUp();
        // 已上架商品的类目信息
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productUp) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        // 设置类目vo
        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setName(productCategory.getCategoryName());
            categoryVo.setType(productCategory.getCategoryType());
            // 设置商品vo
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productUp) {
                // 如果已上架商品的类目type相同
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    productInfoVo.setId(productInfo.getProductId());
                    productInfoVo.setName(productInfo.getProductName());
                    productInfoVo.setPrice(productInfo.getProductPrice());
                    productInfoVo.setDescription(productInfo.getProductDescription());
                    productInfoVo.setIcon(productInfo.getProductIcon());
                    productInfoVoList.add(productInfoVo);
                }
            }
            categoryVo.setProductInfoVoList(productInfoVoList);
            categoryVoList.add(categoryVo);

        }
        return ResultVo.success(categoryVoList);
    }

}
