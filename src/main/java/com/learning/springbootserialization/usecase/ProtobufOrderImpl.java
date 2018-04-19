package com.learning.springbootserialization.usecase;

import com.learning.springbootserialization.gateway.http.proto.OrderProto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.Random;
import java.util.stream.IntStream;

@Service
@Slf4j
public class ProtobufOrderImpl {

    private static final int MAX_PRICE_IN_CENTS = 100000;
    private static final int ORDER_ID = 10000000;
    private static final String PERSON_NAME = "Outback";
    private static final String SKU_CAMISA_SELECAO = "D12-9560-046-04";
    private static final String SKU_GIFT = "121-3434-121-21";
    private static final String SKU_SAPATENIS = "941-6957-219-41";

    public OrderProto.Orders getProtobufOrders(int totalElements) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("ProtobufOrder.getProtobufOrders");
        log.info("Starting task {} ", "ProtobufOrder.getProtobufOrders");

        OrderProto.Orders.Builder orders = OrderProto.Orders.newBuilder();

        IntStream.range(1, totalElements)
                .forEach(
                        i -> {
                            OrderProto.Sku skuSapatenis =
                                    OrderProto.Sku.newBuilder()
                                            .setId(SKU_SAPATENIS)
                                            .setSkuType(OrderProto.Sku.Skutype.NORMAL)
                                            .setPrice(new Random().nextInt(MAX_PRICE_IN_CENTS))
                                            .build();

                            OrderProto.Sku skuCamisaSelecao =
                                    OrderProto.Sku.newBuilder()
                                            .setId(SKU_CAMISA_SELECAO)
                                            .setSkuType(OrderProto.Sku.Skutype.NORMAL)
                                            .setPrice(new Random().nextInt(MAX_PRICE_IN_CENTS))
                                            .build();

                            OrderProto.Sku skuGift =
                                    OrderProto.Sku.newBuilder()
                                            .setId(SKU_GIFT)
                                            .setSkuType(OrderProto.Sku.Skutype.GIFT)
                                            .setPrice(new Random().nextInt(MAX_PRICE_IN_CENTS))
                                            .build();

                            OrderProto.Person person = OrderProto.Person.newBuilder().setName(PERSON_NAME).build();

                            OrderProto.Order order =
                                    OrderProto.Order.newBuilder()
                                            .setOid(String.valueOf(ORDER_ID + i))
                                            .setPerson(person)
                                            .addSku(skuCamisaSelecao)
                                            .addSku(skuSapatenis)
                                            .addSku(skuGift)
                                            .build();
                            orders.addOrders(order);
                        });

        stopWatch.stop();
        log.info(
                "Total time in milliseconds: {}",
                stopWatch.getLastTaskTimeMillis());
        return orders.build();
    }
}
