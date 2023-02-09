package com.example.modsentest.service;

import com.example.modsentest.dto.place.PlaceCreateDto;
import com.example.modsentest.dto.place.PlaceReadDto;
import com.example.modsentest.entity.Place;
import com.example.modsentest.mapper.PlaceMapper;
import com.example.modsentest.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    @Transactional
    public List<PlaceReadDto> findAll() {
        return placeRepository.findAll().stream()
                .map(placeMapper::toReadDto)
                .toList();
    }

    @Transactional
    public Optional<PlaceReadDto> findById(Integer id){
        return placeRepository.findById(id)
                .map(placeMapper::toReadDto);
    }

    @Transactional
    public PlaceReadDto create(PlaceCreateDto editDto){
        return Optional.of(editDto)
                .map(placeMapper::toPlace)
                .map(placeRepository::save)
                .map(placeMapper::toReadDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<PlaceReadDto> update(Integer id, PlaceCreateDto placeCreateDto) {
        Place place=placeMapper.toPlace(placeCreateDto);
        place.setId(id);
        return Optional.of(place)
                .map(placeRepository::update)
                .map(placeMapper::toReadDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        var place = placeRepository.findById(id);
        place.ifPresent(pla -> placeRepository.delete(id));
        return place.isPresent();
    }
}
