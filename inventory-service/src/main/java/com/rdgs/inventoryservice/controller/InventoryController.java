package com.rdgs.inventoryservice.controller;

import com.rdgs.inventoryservice.service.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInStock(@RequestParam(name = "id") String productId, @RequestParam(required = false, defaultValue = "1") Long quantity) {
        return inventoryService.isInStock(productId, quantity);
    }
}
