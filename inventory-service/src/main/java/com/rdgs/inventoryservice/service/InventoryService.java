package com.rdgs.inventoryservice.service;

import com.rdgs.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }


    public boolean isInStock(String productId, Long quantity) {
        return inventoryRepository.findByProductIdAndQuantityGreaterThanEqual(productId, quantity).isPresent();
    }
}
