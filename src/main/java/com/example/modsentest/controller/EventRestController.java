package com.example.modsentest.controller;

import com.example.modsentest.dto.EventFilter;
import com.example.modsentest.dto.event.EventCreateDto;
import com.example.modsentest.dto.event.EventReadDto;
import com.example.modsentest.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RequiredArgsConstructor
@RestController
public class EventRestController {
    private final EventService eventService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EventReadDto> findAll(EventFilter filter) {
        return eventService.findAll(filter);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EventReadDto create(@RequestBody EventCreateDto createDto) {
        return eventService.create(createDto).get();
    }

    @GetMapping("/{id}")
    public EventReadDto findById(@PathVariable("id") Integer id) {
        return eventService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public EventReadDto update(@PathVariable("id") Integer id,
                               @RequestBody EventCreateDto editDto) {
        return eventService.update(id, editDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return eventService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
