package com.example.hotelBooking.service;

import com.example.hotelBooking.dto.HotelDto;
import com.example.hotelBooking.dto.HotelFilterRequest;
import com.example.hotelBooking.dto.HotelPaginatedResponseDto;
import com.example.hotelBooking.entities.Hotel;
import com.example.hotelBooking.exception.ResourceNotFoundException;
import com.example.hotelBooking.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;



    @Override
    public HotelDto CreateNewHotel(HotelDto hotelDto) {
        Hotel hotel =  modelMapper.map(hotelDto, Hotel.class);
         hotel = hotelRepository.save(hotel);
         return  modelMapper.map(hotel, HotelDto.class);

    }

    @Override
    public HotelDto GetHotelById(Long id) {
        Hotel hotel = hotelRepository.
                findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto UpdateHotelById(Long id, HotelDto hotelDto) {
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found"));


        modelMapper.getConfiguration().setSkipNullEnabled(true);

        modelMapper.map(hotelDto, hotel);

        Hotel updatedHotel = hotelRepository.save(hotel);

        return modelMapper.map(updatedHotel, HotelDto.class);
    }


    @Override
    public void DeleteHotelById(Long id) {
        boolean exists = hotelRepository.existsById(id);

        if(!exists) throw new ResourceNotFoundException("Hotel not found");

        hotelRepository.deleteById(id);
        return;
    }

    @Override
    public HotelPaginatedResponseDto<HotelDto> GetAllHotels(HotelFilterRequest request) {
        int page = request.getPage() - 1; // zero-based index
        int limit = request.getLimit();
        String search = request.getSearch();
        String sortByCols = request.getSortByCols();
        String sortByKey = request.getSortByKey();

        Sort sort = sortByKey.equalsIgnoreCase("ASC")
                ? Sort.by(sortByCols).ascending()
                : Sort.by(sortByCols).descending();

        Pageable pageable = PageRequest.of(page, limit, sort);
        Page<Hotel> hotelPage;

        if (search != null && !search.trim().isEmpty()) {
            hotelPage = hotelRepository.searchHotels(search, pageable);
        } else {
            hotelPage = hotelRepository.findAll(pageable);
        }

        List<HotelDto> hotelDtos = hotelPage.getContent()
                .stream()
                .map(hotel -> modelMapper.map(hotel, HotelDto.class))
                .collect(Collectors.toList());

        return new HotelPaginatedResponseDto<>(hotelDtos, hotelPage.getTotalElements());
    }

}
