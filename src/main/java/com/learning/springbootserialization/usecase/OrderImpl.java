package com.learning.springbootserialization.usecase;

import com.learning.springbootserialization.domain.Order;
import com.learning.springbootserialization.domain.Person;
import com.learning.springbootserialization.domain.Sku;
import com.learning.springbootserialization.domain.SkuType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@Slf4j
public class OrderImpl {
    private static final int MAX_PRICE_IN_CENTS = 100000;
    private static final String SKU_SAPATENIS = "941-6957-219-41";
    private static final String SKU_CAMISA_SELECAO = "D12-9560-046-04";
    private static final String SKU_GIFT = "121-3434-121-21";
    private static final int ORDER_ID = 10000000;
    private static final String PERSON_NAME = "Outback";

    public Collection<Order> getOrders(int totalElements) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("OrderImpl.getOrders");
        log.info("Starting task {} ", "OrderImpl.getOrders");

        Collection<Order> orderCollection = new ArrayList<>();

        IntStream.range(1, totalElements)
                .forEach(
                        i -> {
                            final Sku skuSapatenis =
                                    Sku.builder()
                                            .id(SKU_SAPATENIS)
                                            .skutype(SkuType.NORMAL)
                                            .price(new Random().nextInt(MAX_PRICE_IN_CENTS))
                                            .build();

                            final Sku skuCamisaSelecao =
                                    Sku.builder()
                                            .id(SKU_CAMISA_SELECAO)
                                            .skutype(SkuType.NORMAL)
                                            .price(new Random().nextInt(MAX_PRICE_IN_CENTS))
                                            .build();

                            final Sku skuGift =
                                    Sku.builder()
                                            .id(SKU_GIFT)
                                            .skutype(SkuType.GIFT)
                                            .price(new Random().nextInt(MAX_PRICE_IN_CENTS))
                                            .build();

                            Person person = Person.builder().name(PERSON_NAME).build();

                            Order order =
                                    Order.builder()
                                            .oid(String.valueOf(ORDER_ID + i))
                                            .person(person)
                                            .skuCollection(Arrays.asList(skuCamisaSelecao, skuGift, skuSapatenis))
                                            .build();
                            orderCollection.add(order);
                        });

        stopWatch.stop();
        log.info(
                "Total time in milliseconds: {}",
                stopWatch.getLastTaskTimeMillis());
        return orderCollection;
    }
}
