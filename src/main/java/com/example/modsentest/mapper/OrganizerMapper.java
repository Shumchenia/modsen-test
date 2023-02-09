package com.example.modsentest.mapper;

import com.example.modsentest.dto.organizer.OrganizerCreateDto;
import com.example.modsentest.dto.organizer.OrganizerReadDto;
import com.example.modsentest.entity.Organizer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizerMapper {
    OrganizerReadDto toReadDto(Organizer organizer);
    Organizer toOrganizer(OrganizerCreateDto organizerCreateDto);
}
