package integration.com.article.springbootserializationproducer.gateway.http;

import com.article.springbootserialization.proto.OrdersProto;
import com.article.springbootserializationproducer.SpringBootSerializationApplication;
import com.article.springbootserializationproducer.domain.Order;
import com.article.springbootserializationproducer.domain.Person;
import com.article.springbootserializationproducer.domain.Product;
import com.article.springbootserializationproducer.domain.ProductType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = SpringBootSerializationApplication.class)
class OrderControllerTest2 {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String HTTP_LOCALHOST = "http://localhost:";

    @Test
    void testGetOrdersProtoShouldReturn() {
        int totalElements = 10;

        final URI uri = UriComponentsBuilder.fromHttpUrl(HTTP_LOCALHOST + port + "/proto/order/")
                .path(String.valueOf(totalElements))
                .build().encode().toUri();

        final RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();

        final ResponseEntity<OrdersProto.Orders> responseEntity = restTemplate
                .exchange(requestEntity, OrdersProto.Orders.class);

        then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void testGetOrdersProtoShouldReturn10ElemenstInProtobufFormat() throws Exception {
        int totalElements = 10;

        OrdersProto.Orders originalData = getProtoData(totalElements);

        final URI uri = UriComponentsBuilder.fromHttpUrl(HTTP_LOCALHOST + port + "/proto/order/")
                .path(String.valueOf(totalElements))
                .build().encode().toUri();

        final RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();

        final ResponseEntity<OrdersProto.Orders> responseEntity = restTemplate
                .exchange(requestEntity, OrdersProto.Orders.class);

        OrdersProto.Orders responseOrderData = OrdersProto.Orders.parseFrom(responseEntity.getBody().toByteArray());

        then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(responseEntity.getHeaders().getContentType().toString()).isEqualTo("application/x-protobuf;charset=UTF-8");
        then(responseEntity.getHeaders().getFirst("X-Protobuf-Message")).isEqualTo("com.article.springbootserialization.proto.Orders");
        then(responseEntity.getHeaders().getFirst("X-Protobuf-Schema")).isEqualTo("order.proto");
        then(responseOrderData).isEqualTo(originalData);
    }

    @Test
    void testGetOrdersShouldReturn10ElementsInJsonFormat() {
        int totalElements = 10;

        Collection<Order> originalData = jsonOrders(totalElements);

        final URI uri = UriComponentsBuilder.fromHttpUrl(HTTP_LOCALHOST + port + "/order/")
                .path(String.valueOf(totalElements))
                .build().encode().toUri();

        final RequestEntity<Void> requestEntity = RequestEntity
                .get(uri)
                .build();

        final ResponseEntity<Collection<Order>> responseEntity = restTemplate
                .exchange(requestEntity, new ParameterizedTypeReference<>() {
                });

        Collection<Order> responseOrderData = responseEntity.getBody();

        then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        then(responseEntity.getHeaders().getContentType().toString()).isEqualTo("application/json");
        then(responseOrderData).containsAll(originalData);
    }


    private Collection<Order> jsonOrders(int totalElements) {
        final Collection<Order> orderCollection = new ArrayList<>(totalElements);
        int INITIAL_ORDER_ID = 10000000;
        String PERSON_NAME = "John Conceição";
        String ID_PRODUCT_1 = "XXX-2121-333-11";
        String ID_PRODUCT_2 = "YYY-3333-121-21";
        String ID_PRODUCT_3 = "941-6957-219-41";

        IntStream.range(0, totalElements)
                .forEach(
                        iteration -> {
                            final Product product1 =
                                    Product.builder()
                                            .id(ID_PRODUCT_1)
                                            .productType(ProductType.COMMON)
                                            .priceInCents(iteration)
                                            .build();

                            final Product product2 =
                                    Product.builder()
                                            .id(ID_PRODUCT_2)
                                            .productType(ProductType.COMMON)
                                            .priceInCents(iteration)
                                            .build();

                            final Product product3 =
                                    Product.builder()
                                            .id(ID_PRODUCT_3)
                                            .productType(ProductType.COMMON)
                                            .priceInCents(iteration)
                                            .build();

                            final Person person = Person.builder().name(PERSON_NAME).build();

                            final Order order =
                                    Order.builder()
                                            .orderId(String.valueOf(INITIAL_ORDER_ID + iteration))
                                            .person(person)
                                            .productCollection(Arrays.asList(product1, product2, product3))
                                            .build();
                            orderCollection.add(order);
                        });

        return orderCollection;
    }

    private OrdersProto.Orders getProtoData(int totalElements) {
        int INITIAL_ORDER_ID = 10000000;
        String PERSON_NAME = "John Conceição";
        String ID_PRODUCT_1 = "XXX-2121-333-11";
        String ID_PRODUCT_2 = "YYY-3333-121-21";
        String ID_PRODUCT_3 = "941-6957-219-41";

        final OrdersProto.Orders.Builder orders = OrdersProto.Orders.newBuilder();

        IntStream.range(0, totalElements)
                .forEach(
                        iteration -> {
                            final OrdersProto.Product product1 =
                                    OrdersProto.Product.newBuilder()
                                            .setId(ID_PRODUCT_1)
                                            .setProductType(OrdersProto.Product.ProductType.COMMON)
                                            .setPriceInCents(iteration)
                                            .build();

                            final OrdersProto.Product product2 =
                                    OrdersProto.Product.newBuilder()
                                            .setId(ID_PRODUCT_2)
                                            .setProductType(OrdersProto.Product.ProductType.COMMON)
                                            .setPriceInCents(iteration)
                                            .build();

                            final OrdersProto.Product product3 =
                                    OrdersProto.Product.newBuilder()
                                            .setId(ID_PRODUCT_3)
                                            .setProductType(OrdersProto.Product.ProductType.GIFT)
                                            .setPriceInCents(iteration)
                                            .build();

                            final OrdersProto.Person person = OrdersProto.Person.newBuilder().setName(PERSON_NAME).build();

                            final OrdersProto.Order order =
                                    OrdersProto.Order.newBuilder()
                                            .setOrderId(String.valueOf(INITIAL_ORDER_ID + iteration))
                                            .setPerson(person)
                                            .addProduct(product1)
                                            .addProduct(product2)
                                            .addProduct(product3)
                                            .build();
                            orders.addOrders(order);
                        });

        return orders.build();
    }


}
