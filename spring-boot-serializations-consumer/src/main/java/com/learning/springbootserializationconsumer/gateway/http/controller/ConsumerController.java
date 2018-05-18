package com.learning.springbootserializationconsumer.gateway.http.controller;

import com.googlecode.protobuf.format.JsonFormat;
import com.learning.springbootserializationconsumer.domain.Order;
import com.learning.springbootserializationconsumer.usecase.OrderImpl;
import com.learning.springbootserializationconsumer.usecase.ProtobufOrderImpl;
import com.learning.springbootserializationproducer.gateway.http.proto.OrderProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ConsumerController {

    private final ProtobufOrderImpl protobufOrder;
    private final OrderImpl orderImpl;
    private final JsonFormat jsonFormat = new JsonFormat();

    @GetMapping(value = "/proto/formatted/order/{totalElement}")
    public ResponseEntity<String> getFormatedOrdersProto(@PathVariable int totalElement) {
        OrderProto.Orders defaultInstance = protobufOrder.getProtobufOrders(totalElement);

        return ResponseEntity.ok(jsonFormat.printToString(defaultInstance));
    }

    @GetMapping("/order/{totalElement}")
    public ResponseEntity<Collection<Order>> getOrders(@PathVariable int totalElement) {
        return ResponseEntity.ok(orderImpl.getOrders(totalElement));
    }

    @GetMapping(value = "/proto/order/{totalElement}")
    public OrderProto.Orders getOrdersProto(@PathVariable int totalElement) {
        OrderProto.Orders protobufOrders = protobufOrder.getProtobufOrders(totalElement);
        log.info(jsonFormat.printToString(protobufOrders));
        return protobufOrders;
    }
}
