package com.example.hotelBooking.service;

import com.example.hotelBooking.dto.HotelDto;
import com.example.hotelBooking.dto.RoomDto;
import com.example.hotelBooking.entities.Hotel;
import com.example.hotelBooking.entities.Room;
import com.example.hotelBooking.exception.ResourceNotFoundException;
import com.example.hotelBooking.repository.HotelRepository;
import com.example.hotelBooking.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;

    @Override
    @Transactional
    public RoomDto CreateRoom(Long HotelId, RoomDto roomDto) {
        log.info("Room creating  in htel : {}", HotelId);

        Hotel hotel = hotelRepository.findById(HotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel Not Found with ID : {}" + HotelId));

        if (!hotel.getActive()) {
            throw new IllegalStateException("Cannot create room. Hotel is not active.");
        }


        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);

        Room savedRoom = roomRepository.save(room);

        inventoryService.initializeRoomForAYear(savedRoom);

        return modelMapper.map(savedRoom, RoomDto.class);

    }

    @Override
    public List<RoomDto> getAllRoomInHotel(Long hotelId) {
        log.info("get all room in a hotel : {}", hotelId);
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel Not Found with ID : {}" + hotelId));

        return hotel.getRooms()
                    .stream()
                    .map((element) -> modelMapper.map(element, RoomDto.class))
                    .collect(Collectors.toList());


    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                                   .orElseThrow(() -> new ResourceNotFoundException("Room not found by Id" + roomId));

        return modelMapper.map(room, RoomDto.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found by Id" + roomId));

        roomRepository.deleteById(roomId);
        inventoryService.deleteFutureInventory(room);

    }
}
