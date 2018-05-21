package com.learning.springbootserializationconsumer.usecase;

import com.learning.springbootserializationconsumer.domain.Order;
import com.learning.springbootserializationconsumer.gateway.ProducerGateway;
import com.netshoes.springbootserialization.proto.OrdersProto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ProducerGateway producerGateway;

    public Collection<Order> getOrders(int totalElements) {
        return producerGateway.getOrders(totalElements);
    }

    public OrdersProto.Orders getProtobufOrders(int totalElements) {
        return producerGateway.getOrdersProto(totalElements);
    }
}
