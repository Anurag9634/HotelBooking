package com.example.hotelBooking.dto;

import lombok.Data;

@Data
public class HotelFilterRequest {
    private int limit = 10;
    private int offset = 0;
    private int page = 1;
    private String search = "";
    private String sortByCols = "id";
    private String sortByKey = "ASC";
}
