package com.example.kairo.listoflistsback.framework.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface JpaCrudService<T, ID extends Serializable, Y> {
    List<T> findAll();

    List<T> findAll(Sort sort);

    Page<T> findAll(Pageable pageable);

    Page<T> findAll(Pageable pageable, String query, Y filter);

    <S extends T> S save(S entity);

    T saveAndFlush(T entity);

    Iterable<T> save(Iterable<T> iterable);

    void flush();

    T findOne(ID id);

    Optional<T> findById(ID id);

    boolean exists(ID id);

    long count();

    void delete(ID id);

    void delete(T entity);

    void delete(Iterable<? extends T> iterable);

    void deleteAll();
}
