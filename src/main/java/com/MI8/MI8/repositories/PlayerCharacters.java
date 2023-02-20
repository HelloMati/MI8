package com.MI8.MI8.repositories;

import com.MI8.MI8.models.PlayerCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerCharacters extends JpaRepository<PlayerCharacter,Integer> {
}
