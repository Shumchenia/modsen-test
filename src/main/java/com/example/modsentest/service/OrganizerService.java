package com.example.modsentest.service;

import com.example.modsentest.dto.organizer.OrganizerCreateDto;
import com.example.modsentest.dto.organizer.OrganizerReadDto;
import com.example.modsentest.entity.Organizer;
import com.example.modsentest.mapper.OrganizerMapper;
import com.example.modsentest.repository.OrganizerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrganizerService {

    private final OrganizerRepository organizerRepository;
    private final OrganizerMapper organizerMapper;

    @Transactional
    public List<OrganizerReadDto> findAll() {
        return organizerRepository.findAll().stream()
                .map(organizerMapper::toReadDto)
                .toList();
    }

    @Transactional
    public Optional<OrganizerReadDto> findById(Integer id) {
        return organizerRepository.findById(id)
                .map(organizerMapper::toReadDto);
    }

    @Transactional
    public OrganizerReadDto create(OrganizerCreateDto editDto) {
        return Optional.of(editDto)
                .map(organizerMapper::toOrganizer)
                .map(organizerRepository::save)
                .map(organizerMapper::toReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<OrganizerReadDto> update(Integer id,
                                             OrganizerCreateDto organizerCreateEditDto) {
        Organizer organizer=organizerMapper.toOrganizer(organizerCreateEditDto);
        organizer.setId(id);
        return Optional.of(organizer)
                .map(organizerRepository::update)
                .map(organizerMapper::toReadDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        var organizer = organizerRepository.findById(id);
        organizer.ifPresent(org -> organizerRepository.delete(id));
        return organizer.isPresent();
    }
}

