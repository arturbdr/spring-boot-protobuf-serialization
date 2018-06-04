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
public class OrderControllerTest1 {

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

}
