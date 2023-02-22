package com.MI8.MI8.repositories;

import com.MI8.MI8.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Integer> {

    public Optional<Item> findByName(String name);

    public Optional<Integer> findIdByName(String name);

}
