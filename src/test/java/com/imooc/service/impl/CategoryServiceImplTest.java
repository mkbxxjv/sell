package com.imooc.service.impl;

import com.imooc.dataObject.ProductCategory;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    @Transactional
    void findOne() throws Exception{
        ProductCategory pro = categoryService.findOne(1);
        assertEquals(new Integer(1),pro.getCategoryId());
    }

    @Test
    void findAll() throws Exception{
        List<ProductCategory> pro = categoryService.findAll();
        assertNotEquals(0,pro.size());
    }

    @Test
    void findByCategoryTypeIn() {
        List<ProductCategory> pro = categoryService.findByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        assertNotEquals(0,pro.size());
    }

    @Test
    void save() {
        ProductCategory productCategory = new ProductCategory("男生专享",10);
        ProductCategory result = categoryService.save(productCategory);
        assertNotNull(result);
    }
}