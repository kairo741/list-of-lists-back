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
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "url_photo")
    private String urlPhoto;

    @Column(name = "base64photo", columnDefinition = "LONGTEXT")
    private String base64photo;

    @Column(name = "id_list")
    private Long idList;

    @Column(name = "status")
    private String status;

    @Column(name = "create_date")
    private LocalDateTime createDate; // implementado no Serializable

}
