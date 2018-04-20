package com.learning.springbootserializationconsumer.usecase;

import com.learning.springbootserializationconsumer.domain.Order;
import com.learning.springbootserializationconsumer.gateway.http.rest.ProducerRestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderImpl {

    private final ProducerRestTemplate producerRestTemplate;

    public Collection<Order> getOrders(int totalElements) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("OrderImpl.getOrders");
        log.info("Starting task {} ", "OrderImpl.getOrders");

        Collection<Order> orderCollection = producerRestTemplate.getOrders(totalElements);

        stopWatch.stop();
        log.info(
                "Total time in milliseconds: {}",
                stopWatch.getLastTaskTimeMillis());
        return orderCollection;
    }
}
