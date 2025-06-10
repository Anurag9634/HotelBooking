package com.example.hotelBooking.repository;

import com.example.hotelBooking.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
