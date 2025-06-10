package com.example.hotelBooking.controller;

import com.example.hotelBooking.dto.HotelDto;
import com.example.hotelBooking.dto.HotelFilterRequest;
import com.example.hotelBooking.dto.HotelPaginatedResponseDto;
import com.example.hotelBooking.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotel")
@Slf4j
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @PostMapping("/create")
    public ResponseEntity<HotelDto> CreateNewHotel(@RequestBody HotelDto hotelDto)
    {
        log.info("hii {}", hotelDto.getName());
        HotelDto hotel = hotelService.CreateNewHotel(hotelDto);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelDto> GetHotelById(@PathVariable Long hotelId)
    {
        HotelDto hotel = hotelService.GetHotelById(hotelId);
        return ResponseEntity.ok(hotel);
    }

    @PutMapping("/{hotelId}")
    public ResponseEntity<HotelDto> UpdateHotelById(@PathVariable Long hotelId, @RequestBody HotelDto hotelDto)
    {

        HotelDto hotel = hotelService.UpdateHotelById(hotelId, hotelDto);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> DeleteHotelById(@PathVariable Long hotelId)
    {
        hotelService.DeleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/all")
    public ResponseEntity<HotelPaginatedResponseDto<HotelDto>> getAllHotels(@RequestBody HotelFilterRequest request) {
        log.info("i am inside controller");
        HotelPaginatedResponseDto<HotelDto> hotels = hotelService.GetAllHotels(request);
        return ResponseEntity.ok(hotels);
    }
}
