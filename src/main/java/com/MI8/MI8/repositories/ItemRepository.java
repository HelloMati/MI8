package com.MI8.MI8.repositories;

import com.MI8.MI8.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
