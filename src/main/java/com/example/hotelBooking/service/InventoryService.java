package com.example.hotelBooking.service;

import com.example.hotelBooking.entities.Room;

public interface InventoryService {

    void initializeRoomForAYear(Room room);

    void deleteFutureInventory(Room room);
}
