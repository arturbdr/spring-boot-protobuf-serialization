package com.learning.springbootserialization.gateway.http;

import com.learning.springbootserialization.domain.Order;
import com.learning.springbootserialization.gateway.http.proto.OrderProto;
import com.learning.springbootserialization.usecase.OrderImpl;
import com.learning.springbootserialization.usecase.ProtobufOrderImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final ProtobufOrderImpl protobufOrder;
    private final OrderImpl order;

    @GetMapping("/proto/order/{totalElement}")
    public OrderProto.Orders getOrdersProto(@PathVariable int totalElement) {
        return protobufOrder.getProtobufOrders(totalElement);
    }

    @GetMapping("/order/{totalElement}")
    public ResponseEntity<Collection<Order>> getOrders(@PathVariable int totalElement) {
        return ResponseEntity.ok(order.getOrders(totalElement));
    }
}
