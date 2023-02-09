package com.example.modsentest.service;

import com.example.modsentest.dto.EventFilter;
import com.example.modsentest.dto.event.EventCreateDto;
import com.example.modsentest.dto.event.EventReadDto;
import com.example.modsentest.entity.Event;
import com.example.modsentest.mapper.EventMapper;
import com.example.modsentest.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Autowired
    public EventService(EventRepository eventRepository, EventMapper eventMapper) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
    }

    @Transactional
    public Optional<EventReadDto> findById(Integer id) {
        return eventRepository.findById(id)
                .map(eventMapper::toReadDto);
    }

    @Transactional
    public List<EventReadDto> findAll() {
        return eventRepository.findAll().stream()
                .map(eventMapper::toReadDto)
                .toList();
    }

    @Transactional
    public List<EventReadDto> findAll(EventFilter filter) {
        return eventRepository.findAllByFilter(filter).stream()
                .map(eventMapper::toReadDto)
                .toList();
    }

    @Transactional
    public Optional<EventReadDto> create(EventCreateDto event) {
        return Optional.of(event)
                .map(eventMapper::toEvent)
                .map(eventRepository::save)
                .map(eventMapper::toReadDto);
    }

    @Transactional
    public Optional<EventReadDto> update(Integer id, EventCreateDto event) {
        Event event1 = eventMapper.toEvent(event);
        event1.setId(id);
        return Optional.of(event1)
                .map(eventRepository::update)
                .map(eventMapper::toReadDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        var entity = eventRepository.findById(id);
        entity.ifPresent(event -> eventRepository.delete(id));
        return entity.isPresent();
    }
}
