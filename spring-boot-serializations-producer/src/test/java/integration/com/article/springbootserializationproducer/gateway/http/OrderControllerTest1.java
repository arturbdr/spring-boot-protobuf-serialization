package integration.com.article.springbootserializationproducer.gateway.http;

import com.article.springbootserialization.proto.OrdersProto;
import com.article.springbootserializationproducer.SpringBootSerializationApplication;
import com.article.springbootserializationproducer.config.ProtobufHttpMessageConverterConfig;
import com.article.springbootserializationproducer.gateway.http.OrderController;
import com.article.springbootserializationproducer.usecase.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
@ContextConfiguration(classes = {SpringBootSerializationApplication.class, ProtobufHttpMessageConverterConfig.class})
class OrderControllerTest1 {

    @MockBean
    private OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturn10ElementsInProtobufFormat() throws Exception {
        int totalElements = 10;

        OrdersProto.Orders originalData = getProtoData(totalElements);

        when(orderService.getProtobufOrders(totalElements)).thenReturn(originalData);

        MvcResult mvcResult = mockMvc.perform(get("/proto/order/" + totalElements))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", "application/x-protobuf;charset=UTF-8"))
                .andExpect(header().string("X-Protobuf-Message", "com.article.springbootserialization.proto.Orders"))
                .andExpect(header().string("X-Protobuf-Schema", "order.proto"))
                .andReturn();

        OrdersProto.Orders actualOrders = OrdersProto.Orders.parseFrom(mvcResult.getResponse().getContentAsByteArray());
        then(actualOrders).isEqualTo(originalData);
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
