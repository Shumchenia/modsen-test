package com.example.modsentest.repository;

import com.example.modsentest.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface  CrudRepository<K extends Serializable, E extends BaseEntity<K>> {

    E save(E entity);

    boolean delete(K id);

    E update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();
}
