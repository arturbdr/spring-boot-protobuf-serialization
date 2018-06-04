package unit.com.article.springbootserializationproducer.gateway.http;

import com.article.springbootserializationproducer.gateway.http.OrderController;
import com.article.springbootserializationproducer.usecase.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class OrderControllerTest {

    @Mock
    OrderService orderService;

    @InjectMocks
    OrderController orderController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup()
                .build();

    }


    @Test
    public void testGetOrdersProtoShouldReturn() throws Exception {
        int totalElements = 10;

        mockMvc.perform(get("/proto/order/" + totalElements))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme