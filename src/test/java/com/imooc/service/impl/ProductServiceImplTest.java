package com.imooc.service.impl;

import com.imooc.dataObject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    void findOne() {
        ProductInfo pro = productService.findOne("123456");
        Assertions.assertEquals("123456",pro.getProductId());
    }

    @Test
    void findUpAll() {

        List<ProductInfo> proList = productService.findUpAll();
        Assertions.assertNotEquals(0,proList.size());

    }

    @Test
    void findAll() throws Exception{

        PageRequest request = PageRequest.of(0,2);
        Page<ProductInfo> result = productService.findAll(request);
        //System.out.println(result.getTotalElements());
        assertNotEquals(0,result.getTotalElements());

    }

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal("3.2"));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("很好吃的虾");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);

        ProductInfo save = productService.save(productInfo);
        assertNotNull(save);
    }
}