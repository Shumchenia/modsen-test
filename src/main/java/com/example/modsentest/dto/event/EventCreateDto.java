package com.example.modsentest.dto.event;

import java.time.LocalDateTime;

public record EventCreateDto(String title,
                             String description,
                             LocalDateTime date,
                             Integer placeId,
                             Integer organizerId) {
}
