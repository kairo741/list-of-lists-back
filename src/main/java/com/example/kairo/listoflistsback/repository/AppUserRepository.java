package com.example.kairo.listoflistsback.repository;

import com.example.kairo.listoflistsback.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
//    Optional<AppUser> findById(Long id);
}
