package com.MI8.MI8.repositories;

import com.MI8.MI8.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerCharacterRepository extends JpaRepository<Player,Integer> {
}
