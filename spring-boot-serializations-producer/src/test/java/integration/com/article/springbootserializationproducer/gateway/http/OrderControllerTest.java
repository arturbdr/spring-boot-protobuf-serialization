package integration.com.article.springbootserializationproducer.gateway.http;

import com.article.springbootserializationproducer.SpringBootSerializationApplication;
import com.article.springbootserializationproducer.gateway.http.OrderController;
import com.article.springbootserializationproducer.usecase.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
@ContextConfiguration(classes = SpringBootSerializationApplication.class)
public class OrderControllerTest {

    @MockBean
    OrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetOrdersProtoShouldReturn() throws Exception {
        int totalElements = 10;

        mockMvc.perform(get("/proto/order/" + totalElements))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetOrders() throws Exception {
//        when(orderService.getOrders(anyInt())).thenReturn(Arrays.<Order>asList(new Order("orderId", new Person("name"), Arrays.<Product>asList(new Product("id", 0, ProductType.GIFT)))));
//
//        ResponseEntity<Collection<Order>> result = orderController.getOrders(0);
//        Assert.assertEquals(null, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme