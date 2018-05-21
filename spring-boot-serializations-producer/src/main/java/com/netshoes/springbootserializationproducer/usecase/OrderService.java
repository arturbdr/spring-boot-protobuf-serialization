package com.netshoes.springbootserializationproducer.usecase;

import com.netshoes.springbootserialization.gateway.http.proto.OrdersProto;
import com.netshoes.springbootserializationproducer.domain.Order;
import com.netshoes.springbootserializationproducer.gateway.OrderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderGateway orderGateway;

    public Collection<Order> getOrders(int totalElements) {
        return orderGateway.regularOrders(totalElements);
    }

    public OrdersProto.Orders getProtobufOrders(int totalElements) {
        return orderGateway.protobufOrders(totalElements);
    }
}
