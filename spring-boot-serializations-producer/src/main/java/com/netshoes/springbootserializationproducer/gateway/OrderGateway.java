package com.netshoes.springbootserializationproducer.gateway;

import com.netshoes.springbootserializationproducer.domain.Order;
import com.netshoes.springbootserializationproducer.gateway.http.proto.OrdersProto;

import java.util.Collection;

public interface OrderGateway {

    Collection<Order> regularOrders(int totalElements);
    OrdersProto.Orders protobufOrders(int totalElements);
}
