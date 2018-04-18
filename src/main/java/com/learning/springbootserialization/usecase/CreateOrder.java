package com.learning.springbootserialization.usecase;

import com.learning.springbootserialization.gateway.kafka.proto.OrderProto;

public class CreateOrder {

    public static void main(String[] args){

        OrderProto.Sku skuSapatenis = OrderProto.Sku.newBuilder()
                .setId("941-6957-219")
                .setSkuType(OrderProto.Sku.Skutype.NORMAL)
                .setPrice(42990)
                .build();


        OrderProto.Sku skuCamisaSelecao = OrderProto.Sku.newBuilder()
                .setId("D12-9560-046")
                .setSkuType(OrderProto.Sku.Skutype.NORMAL)
                .setPrice(24990)
                .build();

        OrderProto.Person person = OrderProto.Person.newBuilder()
                .setName("This is John")
                .build();

        OrderProto.Order order = OrderProto.Order.newBuilder()
                .setOid("12345678910")
                .setPerson(person)
                .addSku(skuCamisaSelecao)
                .addSku(skuSapatenis)
                .build();

    System.out.println("order = " + order);
    }
}
