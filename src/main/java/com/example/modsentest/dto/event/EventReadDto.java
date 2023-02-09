package com.example.modsentest.dto.event;

import com.example.modsentest.dto.organizer.OrganizerReadDto;
import com.example.modsentest.dto.place.PlaceReadDto;

import java.time.LocalDateTime;

public record EventReadDto(Integer id,
                           String title,
                           String description,
                           LocalDateTime date,
                           PlaceReadDto place,
                           OrganizerReadDto organizer) {
}
