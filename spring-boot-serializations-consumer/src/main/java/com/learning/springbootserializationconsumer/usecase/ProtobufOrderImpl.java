package com.learning.springbootserializationconsumer.usecase;

import com.learning.springbootserializationconsumer.gateway.http.rest.ProducerRestTemplate;
import com.learning.springbootserializationproducer.gateway.http.proto.OrderProto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProtobufOrderImpl {
    private final ProducerRestTemplate producerRestTemplate;

    public OrderProto.Orders getProtobufOrders(int totalElements) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("ProtobufOrder.getProtobufOrders");
        log.info("Starting task {} ", "ProtobufOrder.getProtobufOrders");

        OrderProto.Orders orders = producerRestTemplate.getOrdersProto(totalElements);
        stopWatch.stop();
        log.info("Total time in milliseconds: {}",
                stopWatch.getLastTaskTimeMillis());
        return orders;
    }
}
