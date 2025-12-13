package com.rdgs.inventoryservice.repository;

import com.rdgs.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductIdAndQuantityGreaterThanEqual(String productId, Long quantityIsGreaterThan);
}
