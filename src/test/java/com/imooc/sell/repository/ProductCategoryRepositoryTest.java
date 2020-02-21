package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void add(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("热销榜");
        productCategory.setCategoryType(1);
        ProductCategory result = repository.save(productCategory);
        System.out.println(result);
    }

    @Test
    public void delete(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(3);
        repository.delete(productCategory);
    }

    @Test
    public void update(){
        ProductCategory productCategory = repository.findOne(1);
        productCategory.setCategoryName("热销榜单");
        ProductCategory result = repository.save(productCategory);
        System.out.println(result);
    }

    @Test
    public void list(){
        List<Integer> categoryType = Arrays.asList(1,2);
        List<ProductCategory> result = repository.findByCategoryTypeIn(categoryType);
        System.out.println(result);
    }
}