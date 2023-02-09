package com.example.modsentest.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
public class EventFilter {
    String title;
    String organizer;
    LocalDateTime time;
}
