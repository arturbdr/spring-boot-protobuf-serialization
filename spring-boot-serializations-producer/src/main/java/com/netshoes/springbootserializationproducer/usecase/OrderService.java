package com.netshoes.springbootserializationproducer.usecase;

import com.netshoes.springbootserializationproducer.domain.Order;
import com.netshoes.springbootserializationproducer.gateway.OrderGateway;
import com.netshoes.springbootserializationproducer.gateway.http.proto.OrdersProto;
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
