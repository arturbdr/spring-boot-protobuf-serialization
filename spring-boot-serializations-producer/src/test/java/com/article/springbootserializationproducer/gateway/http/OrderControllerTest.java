package com.article.springbootserializationproducer.gateway.http;

import com.article.springbootserialization.proto.OrdersProto;
import com.article.springbootserializationproducer.domain.Order;
import com.article.springbootserializationproducer.domain.Person;
import com.article.springbootserializationproducer.domain.Product;
import com.article.springbootserializationproducer.domain.ProductType;
import com.article.springbootserializationproducer.usecase.OrderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collection;

import static org.mockito.Mockito.*;

public class OrderControllerTest {
    @Mock
    OrderService orderService;
    @Mock
    Logger log;
    @InjectMocks
    OrderController orderController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrdersProto() throws Exception {
        when(orderService.getProtobufOrders(anyInt())).thenReturn(null);

        ResponseEntity<OrdersProto.Orders> result = orderController.getOrdersProto(0);
        Assert.assertEquals(null, result);
    }

    @Test
    public void testGetOrders() throws Exception {
        when(orderService.getOrders(anyInt())).thenReturn(Arrays.<Order>asList(new Order("orderId", new Person("name"), Arrays.<Product>asList(new Product("id", 0, ProductType.GIFT)))));

        ResponseEntity<Collection<Order>> result = orderController.getOrders(0);
        Assert.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme