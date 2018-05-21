package com.netshoes.springbootserializationproducer.gateway;

import com.netshoes.springbootserialization.proto.OrdersProto;
import com.netshoes.springbootserializationproducer.domain.Order;

import java.util.Collection;

public interface OrderGateway {

    Collection<Order> regularOrders(int totalElements);
    OrdersProto.Orders protobufOrders(int totalElements);
}
