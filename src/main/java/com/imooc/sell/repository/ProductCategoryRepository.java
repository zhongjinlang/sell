package com.imooc.sell.repository;

import com.imooc.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    /**
     * 通过类目编号 查询所有类目
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
