package com.example.hotelBooking.repository;

import com.example.hotelBooking.entities.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query("SELECT h FROM Hotel h WHERE " +
            "LOWER(h.name) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(h.city) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
            "LOWER(h.contactInfo.address) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Hotel> searchHotels(@Param("search") String search, Pageable pageable);

}
