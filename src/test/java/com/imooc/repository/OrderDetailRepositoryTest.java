package com.imooc.repository;

import com.imooc.dataObject.OrderDetail;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("123456710");
        orderDetail.setOrderId("111112");
        orderDetail.setProductIcon("http://xxxx.jpg");
        orderDetail.setProductId("1111113");
        orderDetail.setProductName("蛋炒饭");
        orderDetail.setProductPrice(new BigDecimal("1.3"));
        orderDetail.setProductQuantity(2);

        OrderDetail result = repository.save(orderDetail);
        assertNotNull(result);
    }

    @Test
    @Transactional
    void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("111112");
        assertNotEquals(0,orderDetailList.size());
    }
}