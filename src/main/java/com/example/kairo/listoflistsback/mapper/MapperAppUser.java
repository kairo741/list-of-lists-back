package com.example.kairo.listoflistsback.mapper;

import com.example.kairo.listoflistsback.dto.AppUserDTO;
import com.example.kairo.listoflistsback.entity.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperAppUser {

    AppUser toEntity(AppUserDTO dto);


}
