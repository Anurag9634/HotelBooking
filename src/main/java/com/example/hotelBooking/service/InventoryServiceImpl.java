package com.example.hotelBooking.service;

import com.example.hotelBooking.entities.Inventory;
import com.example.hotelBooking.entities.Room;
import com.example.hotelBooking.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;

    @Override
    public void initializeRoomForAYear(Room room) {

        LocalDate startDate = LocalDate.now();
        List<Inventory> inventoryList = new ArrayList<>();

        for(int i =0; i<=365; i++)
        {
            LocalDate date = startDate.plusDays(i);

               Inventory inventory = Inventory.builder()
                                               .room(room)
                                               .hotel(room.getHotel())
                                                .date(date)
                                               .totalCount(room.getTotalCount())
                                                .bookedCount(0)
                                               .surgeFactor(BigDecimal.ONE)
                                               .price(room.getBasePrice())
                                                .city(room.getHotel().getCity())
                                                .closed(false)
                                                .build();

               inventoryList.add(inventory);

        }

        inventoryRepository.saveAll(inventoryList);


    }

    @Override
    public void deleteFutureInventory(Room room) {
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteByDateAfterAndRoom(today, room);
    }
}
