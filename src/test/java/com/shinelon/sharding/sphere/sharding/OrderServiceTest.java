package com.shinelon.sharding.sphere.sharding;

import com.shinelon.sharding.sphere.SphereApplicationTests;
import com.shinelon.sharding.sphere.service.OrderService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;

/**
 * @ClassName OrderServiceTest
 * @Author syq
 * @Date 2019/9/26 17:14
 **/
public class OrderServiceTest extends SphereApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void transTest() throws ParseException {
        orderService.save();
    }
}
