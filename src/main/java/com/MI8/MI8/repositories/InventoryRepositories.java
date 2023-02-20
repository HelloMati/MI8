package com.MI8.MI8.repositories;

import com.MI8.MI8.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepositories extends JpaRepository<Inventory,Integer> {
}
