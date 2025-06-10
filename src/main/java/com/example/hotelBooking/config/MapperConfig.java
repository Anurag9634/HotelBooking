package com.example.hotelBooking.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//registering ModelMapper as a bean
@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
