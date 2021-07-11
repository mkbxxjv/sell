package com.imooc.service.impl;

import com.imooc.dataObject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String OPENID = "ew3euwhd7sjw9diwkq";
    private final String ORDER_ID="1624996970759176767";

    @Test
    void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("希露菲1");
        orderDTO.setBuyerAddress("慕课网");
        orderDTO.setBuyerPhone("12345678910");
        orderDTO.setBuyerOpenid(OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("123456");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123457");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单】result={}",result);
        assertNotNull(result);
    }

    @Test
    void findOne() {

        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}",result);
        assertEquals(ORDER_ID,result.getOrderId());

    }

    @Test
    void findList() {
        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<OrderDTO> result = orderService.findList(OPENID, pageRequest);
        assertNotEquals(0,result.getTotalElements());
    }

    @Test
    void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        assertEquals(OrderStatusEnum.CANCEL.getCODE(),result.getOrderStatus());
    }

    @Test
    void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        assertEquals(OrderStatusEnum.FINISHED.getCODE(),result.getOrderStatus());
    }

    @Test
    void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        assertEquals(PayStatusEnum.SUCCESS.getCODE(),result.getPayStatus());
    }

}