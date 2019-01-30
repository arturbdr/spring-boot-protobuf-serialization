package unit.com.article.springbootserializationproducer.gateway.http;

import com.article.springbootserialization.proto.OrdersProto;
import com.article.springbootserializationproducer.domain.Order;
import com.article.springbootserializationproducer.domain.Person;
import com.article.springbootserializationproducer.domain.Product;
import com.article.springbootserializationproducer.domain.ProductType;
import com.article.springbootserializationproducer.gateway.http.OrderController;
import com.article.springbootserializationproducer.usecase.OrderService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.BDDAssertions;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest {

    private OrderService orderService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        orderService = mock(OrderService.class);
        OrderController orderController = new OrderController(orderService);
        mockMvc = MockMvcBuilders.standaloneSetup(orderController)
                .setMessageConverters(new ProtobufHttpMessageConverter(), new MappingJackson2HttpMessageConverter())
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldReturn10ElementsInProtobufFormat() throws Exception {
        int totalElements = 10;

        OrdersProto.Orders originalData = getProtoData(10);

        when(orderService.getProtobufOrders(10)).thenReturn(originalData);

        MvcResult mvcResult = mockMvc.perform(get("/proto/order/" + totalElements))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/x-protobuf;charset=UTF-8"))
                .andExpect(header().string("X-Protobuf-Message", "com.article.springbootserialization.proto.Orders"))
                .andExpect(header().string("X-Protobuf-Schema", "order.proto"))
                .andReturn();

        OrdersProto.Orders actualOrders = OrdersProto.Orders.parseFrom(mvcResult.getResponse().getContentAsByteArray());
        then(actualOrders).isEqualToComparingFieldByFieldRecursively(originalData);
    }

    @Test
    public void shouldReturn10ElementsInJsonFormat() throws Exception {
        int totalElements = 10;

        Collection<Order> originalData = jsonOrders(10);

        when(orderService.getOrders(10)).thenReturn(originalData);

        MvcResult mvcResult = mockMvc.perform(get("/order/" + totalElements))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/json;charset=UTF-8"))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        Collection<Order> returnedData = objectMapper.readValue(contentAsString, new TypeReference<Collection<Order>>() {
        });

        assertTrue(returnedData.containsAll(originalData));
    }


    private Collection<Order> jsonOrders(int totalElements) {
        int PRICE_IN_CENTS = 100000;
        int INITIAL_ORDER_ID = 10000000;
        String PERSON_NAME = "John Conceição";
        String ID_PRODUCT_1 = "XXX-2121-333-11";
        String ID_PRODUCT_2 = "YYY-3333-121-21";
        String ID_PRODUCT_3 = "941-6957-219-41";
        final Collection<Order> orderCollection = new ArrayList<>(totalElements);

        IntStream.range(0, totalElements)
                .forEach(
                        iteration -> {
                            final Product product1 =
                                    Product.builder()
                                            .id(ID_PRODUCT_1)
                                            .productType(ProductType.COMMON)
                                            .priceInCents(new Random().nextInt(PRICE_IN_CENTS))
                                            .build();

                            final Product product2 =
                                    Product.builder()
                                            .id(ID_PRODUCT_2)
                                            .productType(ProductType.COMMON)
                                            .priceInCents(new Random().nextInt(PRICE_IN_CENTS))
                                            .build();

                            final Product product3 =
                                    Product.builder()
                                            .id(ID_PRODUCT_3)
                                            .productType(ProductType.COMMON)
                                            .priceInCents(new Random().nextInt(PRICE_IN_CENTS))
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
        int PRICE_IN_CENTS = 100000;
        int INITIAL_ORDER_ID = 10000000;
        final String PERSON_NAME = "John Conceição";
        final OrdersProto.Orders.Builder orders = OrdersProto.Orders.newBuilder();
        IntStream.range(0, totalElements)
                .forEach(
                        iteration -> {
                            final OrdersProto.Product product1 =
                                    OrdersProto.Product.newBuilder()
                                            .setId("123")
                                            .setProductType(OrdersProto.Product.ProductType.COMMON)
                                            .setPriceInCents(new Random().nextInt(PRICE_IN_CENTS))
                                            .build();

                            final OrdersProto.Product product2 =
                                    OrdersProto.Product.newBuilder()
                                            .setId("456")
                                            .setProductType(OrdersProto.Product.ProductType.COMMON)
                                            .setPriceInCents(new Random().nextInt(PRICE_IN_CENTS))
                                            .build();

                            final OrdersProto.Product product3 =
                                    OrdersProto.Product.newBuilder()
                                            .setId("789")
                                            .setProductType(OrdersProto.Product.ProductType.GIFT)
                                            .setPriceInCents(new Random().nextInt(PRICE_IN_CENTS))
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