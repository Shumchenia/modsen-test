package com.example.modsentest.mapper;

import com.example.modsentest.dto.place.PlaceCreateDto;
import com.example.modsentest.dto.place.PlaceReadDto;
import com.example.modsentest.entity.Place;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    PlaceReadDto toReadDto(Place place);
    Place toPlace(PlaceCreateDto placeCreateDto);
}
