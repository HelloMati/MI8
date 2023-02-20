package com.MI8.MI8.repositories;

import com.MI8.MI8.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game,Integer> {
}
