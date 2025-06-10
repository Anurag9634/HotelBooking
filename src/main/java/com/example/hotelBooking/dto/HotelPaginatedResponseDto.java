package com.example.hotelBooking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class HotelPaginatedResponseDto<T> {
    private List<T> data;
    private long totalCount;
}