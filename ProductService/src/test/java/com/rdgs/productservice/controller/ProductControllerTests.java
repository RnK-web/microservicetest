package com.rdgs.productservice.controller;

import com.rdgs.productservice.model.Product;
import com.rdgs.productservice.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.mongodb.MongoDBContainer;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @ServiceConnection
    private static final  MongoDBContainer mongo = new MongoDBContainer("mongo:latest");

    private Product product1;

    @BeforeAll
    static void beforeAll() {
        mongo.start();
    }

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
        product1 = new Product(null, "iphone", 1000);
        List<Product> products = List.of(
                product1,
                new Product(null, "mouse", 60),
                new Product(null, "keyboard", 200)
        );
        productRepository.saveAll(products);
    }

    @Test
    void shouldFindAllProducts() throws Exception {
        mockMvc.perform(get("/api/v1/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.products", hasSize(3)));
    }

    @Test
    void shouldCreateProduct() throws Exception {
        var body = """
                {
                "label":"Mug",
                "price":5
                }
                """;
        mockMvc.perform(post("/api/v1/products").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/v1/products/{id}", product1.getId())).andExpect(status().isNoContent());

        mockMvc.perform(get("/api/v1/products"))
                .andExpect(jsonPath("$.products", hasSize(2)));
    }

}
