package com.imooc.repository;

import com.imooc.dataObject.ProductInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    void testSave(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("皮蛋粥");
        productInfo.setProductPrice(new BigDecimal("3.2"));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("很好喝的粥");
        productInfo.setProductIcon("http://xxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);

        ProductInfo result = repository.save(productInfo);
        assertNotNull(result);
    }

    @Test
    void findByProductStatus() {
        List<ProductInfo> pro = repository.findByProductStatus(0);
        assertNotEquals(0,pro.size());
    }
}