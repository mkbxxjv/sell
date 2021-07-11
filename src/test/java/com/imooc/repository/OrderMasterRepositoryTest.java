package com.imooc.repository;

import com.imooc.dataObject.OrderMaster;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110110";

    @Test
    void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123457");
        orderMaster.setBuyerName("蓝蓝");
        orderMaster.setBuyerPhone("12345678912");
        orderMaster.setBuyerAddress("慕课网");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal("1000"));

        OrderMaster result = repository.save(orderMaster);
        assertNotNull(result);
    }

    @Test
    void findByBuyerOpenid() {
        PageRequest request = PageRequest.of(1, 3);
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, request);
        assertNotEquals(0,result.getTotalElements());
        //System.out.println(result.getTotalElements());
    }
}