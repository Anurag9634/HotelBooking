package com.example.hotelBooking.service;

import com.example.hotelBooking.dto.RoomDto;

import java.util.List;

public interface RoomService {

    RoomDto CreateRoom(Long HotelId, RoomDto roomDto);

    List<RoomDto> getAllRoomInHotel(Long hotelId);

    RoomDto getRoomById(Long roomId);

    void deleteRoomById(Long roomId);


}
