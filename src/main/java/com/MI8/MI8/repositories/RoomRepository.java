package com.MI8.MI8.repositories;

import com.MI8.MI8.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    public Optional<Room> findByRoomName(String name);
}
