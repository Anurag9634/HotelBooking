package com.example.hotelBooking.service;

import com.example.hotelBooking.dto.HotelDto;
import com.example.hotelBooking.dto.HotelFilterRequest;
import com.example.hotelBooking.dto.HotelPaginatedResponseDto;
import com.example.hotelBooking.entities.Hotel;

import java.util.List;

public interface HotelService {
    HotelDto CreateNewHotel(HotelDto hotel);

    HotelDto GetHotelById(Long id);

    HotelDto UpdateHotelById(Long id, HotelDto hotelDto);

    void DeleteHotelById(Long id);

    HotelPaginatedResponseDto<HotelDto> GetAllHotels(HotelFilterRequest request);
}
