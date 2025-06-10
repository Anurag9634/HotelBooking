package com.example.hotelBooking.repository;

import com.example.hotelBooking.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
