package com.rdgs.orderservice.controller;

import com.rdgs.orderservice.repository.OrderRepository;
import com.rdgs.orderservice.service.feignclient.FeignInventoryClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.mysql.MySQLContainer;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderControllerTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MockMvc mockMvc;

    @ServiceConnection
    private static final MySQLContainer mysqlContainer = new MySQLContainer("mysql:9.5.0");

    @MockitoBean
    private FeignInventoryClient feignInventoryClient; // Mocking external API calls

    @BeforeAll
    static void start() {
        mysqlContainer.start();
    }

    @BeforeEach
    void setup() {
        orderRepository.deleteAll();
    }

    @Test
    public void shouldCreateOrder() throws Exception {
        var body = """
                {
                "productId":"product2",
                "quantity":1,
                "price":10}
                """;
        when(feignInventoryClient.isInStock("product2", 1)).thenReturn(true);

        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldNotCreateOrder() throws Exception {
        var body = """
                {
                "productId":"product2",
                "quantity":1000,
                "price":100000}
                """;
        when(feignInventoryClient.isInStock("product2", 1000)).thenReturn(false);

        mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest());
    }
}
