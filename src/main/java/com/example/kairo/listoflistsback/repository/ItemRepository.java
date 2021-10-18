package com.example.kairo.listoflistsback.repository;

import com.example.kairo.listoflistsback.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
//    Optional<Item> findByItem(@NotNull Item item);
}
