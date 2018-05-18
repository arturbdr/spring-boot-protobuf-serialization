package com.netshoes.springbootserializationproducer.gateway.http;

import com.netshoes.springbootserializationproducer.domain.Order;
import com.netshoes.springbootserializationproducer.gateway.http.proto.OrdersProto;
import com.netshoes.springbootserializationproducer.usecase.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/proto/order/{totalElement}")
    public OrdersProto.Orders getOrdersProto(@PathVariable int totalElement) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start("getOrdersProto");

        final OrdersProto.Orders protobufOrders = orderService.getProtobufOrders(totalElement);

        stopWatch.stop();
        log.info("Total time in milliseconds getOrdersProto: {}", stopWatch.getLastTaskTimeMillis());
        return protobufOrders;

    }

    @GetMapping("/order/{totalElement}")
    public ResponseEntity<Collection<Order>> getOrders(@PathVariable int totalElement) {
        final StopWatch stopWatch = new StopWatch();
        stopWatch.start("getOrders");

        final Collection<Order> orders = orderService.getOrders(totalElement);

        stopWatch.stop();
        log.info("Total time in milliseconds getOrders: {}", stopWatch.getLastTaskTimeMillis());
        return ResponseEntity.ok(orders);
    }
}
