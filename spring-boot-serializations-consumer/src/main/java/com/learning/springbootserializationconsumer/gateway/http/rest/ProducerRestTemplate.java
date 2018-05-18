package com.learning.springbootserializationconsumer.gateway.http.rest;

import com.learning.springbootserializationconsumer.config.ProducerPropertiesConfiguration;
import com.learning.springbootserializationconsumer.domain.Order;
import com.learning.springbootserializationproducer.gateway.http.proto.OrderProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@Component
public class ProducerRestTemplate {

    private final ProducerPropertiesConfiguration producerPropertiesConfiguration;
    private final RestTemplate protoRestTemplate;
    private final RestTemplate defaultRestTemplate;

    @Autowired
    public ProducerRestTemplate(ProducerPropertiesConfiguration producerPropertiesConfiguration,
                                @Qualifier("protoRestTemplate") RestTemplate protoRestTemplate,
                                @Qualifier("defaultRestTemplate") RestTemplate defaultRestTemplate) {
        this.producerPropertiesConfiguration = producerPropertiesConfiguration;
        this.protoRestTemplate = protoRestTemplate;
        this.defaultRestTemplate = defaultRestTemplate;
    }

    public OrderProto.Orders getOrdersProto(int totalElements) {

        final URI uri = UriComponentsBuilder.fromHttpUrl(producerPropertiesConfiguration.getProducerProtobufUrl())
                .path(String.valueOf(totalElements))
                .build().encode().toUri();

        final RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();

        final ResponseEntity<OrderProto.Orders> responseEntity = protoRestTemplate.exchange(requestEntity, OrderProto.Orders.class);
        return responseEntity.getBody();
    }

    public Collection<Order> getOrders(int totalElements) {
        final URI uri = UriComponentsBuilder.fromHttpUrl(producerPropertiesConfiguration.getProducerJsonUrl())
                .path(String.valueOf(totalElements))
                .build().encode().toUri();

        final RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();

        final ResponseEntity<Collection<Order>> responseEntity = defaultRestTemplate.exchange(requestEntity, new ParameterizedTypeReference<Collection<Order>>() {
        });
        return responseEntity.getBody();
    }
}