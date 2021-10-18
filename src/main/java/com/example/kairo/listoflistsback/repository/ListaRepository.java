package com.example.kairo.listoflistsback.repository;

import com.example.kairo.listoflistsback.entity.Lista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaRepository extends JpaRepository<Lista, Long> {
}
