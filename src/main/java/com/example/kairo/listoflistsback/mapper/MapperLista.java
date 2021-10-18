package com.example.kairo.listoflistsback.mapper;

import com.example.kairo.listoflistsback.dto.ListaDTO;
import com.example.kairo.listoflistsback.entity.Lista;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperLista {

    Lista toEntity(ListaDTO dto);


}
