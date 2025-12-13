package com.rdgs.inventoryservice.controller;

import com.rdgs.inventoryservice.model.Inventory;
import com.rdgs.inventoryservice.repository.InventoryRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.mysql.MySQLContainer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class InventoryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InventoryRepository inventoryRepository;

    @ServiceConnection
    private static final MySQLContainer container = new MySQLContainer("mysql:9.5.0");

    private Inventory item1;

    @BeforeAll
    static void init() {
        container.start();
    }

    @BeforeEach
    void setup() {
        inventoryRepository.deleteAll();
        item1 = new Inventory(null, "product1", 10L);
        inventoryRepository.save(item1);
    }

    @Test
    public void shouldBeInStock() throws Exception {
        mockMvc.perform(get("/api/v1/inventory")
                        .param("id", item1.getProductId())
                        .param("quantity", item1.getQuantity().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(true)));
    }

    @Test
    public void shouldNotBeInStock() throws Exception {
        mockMvc.perform(get("/api/v1/inventory")
                        .param("id", item1.getProductId())
                        .param("quantity", Long.toString(item1.getQuantity() + 1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is(false)));
    }
}
