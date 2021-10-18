package com.example.kairo.listoflistsback.mapper;

import com.example.kairo.listoflistsback.dto.ItemDTO;
import com.example.kairo.listoflistsback.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperItem {

    Item toEntity(ItemDTO dto);


}
