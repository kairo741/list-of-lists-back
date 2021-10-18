package com.example.kairo.listoflistsback.framework.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JpaCrudRepository<T, Y> {
    Page<T> pageByFilter(Pageable pageable, Y filter);
    List<T> findAllByFilter(Y filter);
}
