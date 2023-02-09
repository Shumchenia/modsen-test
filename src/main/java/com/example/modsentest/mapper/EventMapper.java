package com.example.modsentest.mapper;

import com.example.modsentest.dto.event.EventCreateDto;
import com.example.modsentest.dto.event.EventReadDto;
import com.example.modsentest.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventMapper {


    EventReadDto toReadDto(Event event);

    @Mapping(target = "place.id",source ="placeId")
    @Mapping(target = "organizer.id",source ="organizerId")
    Event toEvent(EventCreateDto event);

}
