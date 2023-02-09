package com.example.modsentest.controller;

import com.example.modsentest.dto.organizer.OrganizerCreateDto;
import com.example.modsentest.dto.organizer.OrganizerReadDto;
import com.example.modsentest.service.OrganizerService;
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
@RequestMapping("/organizer")
public class OrganizerRestController {

    private final OrganizerService organizerService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrganizerReadDto> findAll() {
        return organizerService.findAll();
    }

    @GetMapping("/{id}")
    public OrganizerReadDto findById(@PathVariable("id") Integer id) {
        return organizerService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizerReadDto create(@RequestBody OrganizerCreateDto createDto) {
        return organizerService.create(createDto);
    }

    @PutMapping("/{id}")
    public OrganizerReadDto update(@PathVariable("id") Integer id,
                                   @RequestBody OrganizerCreateDto editDto) {
        return organizerService.update(id, editDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return organizerService.delete(id)
                ? noContent().build()
                : notFound().build();
    }
}
