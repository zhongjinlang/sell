package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.ProductCategory;
import com.imooc.sell.service.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findOne() {
        ProductCategory result = categoryService.findOne(1);
        System.out.println(result);
    }

    @Test
    public void findAll() {
        List<ProductCategory> all = categoryService.findAll();
        for (ProductCategory productCategory : all) {
            System.out.println(productCategory);
        }
    }

    @Test
    public void findByCategoryTypeIn() {

        List<ProductCategory> result = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2));
        for (ProductCategory productCategory : result) {
            System.out.println(productCategory.toString());
        }
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女生最爱");
        productCategory.setCategoryType(3);
        ProductCategory result = categoryService.save(productCategory);
        System.out.println(result);
    }
}