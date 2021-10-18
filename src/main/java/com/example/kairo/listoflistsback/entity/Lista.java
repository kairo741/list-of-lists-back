package com.example.kairo.listoflistsback.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false, of = {"id"})
@Entity
@Table(name = "lista")
public class Lista implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private Long icon;

    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "status")
    private String status;

    @Column(name = "create_date")
    private LocalDateTime createDate; // implementado no Serializable

}
