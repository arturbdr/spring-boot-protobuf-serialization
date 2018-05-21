package com.learning.springbootserializationconsumer.gateway;

import com.learning.springbootserializationconsumer.domain.Order;
import com.netshoes.springbootserialization.proto.OrdersProto;

import java.util.Collection;

public interface ProducerGateway {
    OrdersProto.Orders getOrdersProto(int totalElements);

    Collection<Order> getOrders(int totalElements);
}
