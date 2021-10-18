package com.example.kairo.listoflistsback.mapper;

import com.example.kairo.listoflistsback.dto.UserDTO;
import com.example.kairo.listoflistsback.entity.User;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperUser {

    @Mappings({
            @Mapping(target = "roles", source = "entity", qualifiedByName = "roles"),
    })
    UserDTO toDto(User entity);

//    User toEntity(UserDTO dto);

    @Named("roles")
    default List<String> roles(User entity) {
        if (entity != null && entity.getRoles() != null) {
            List<String> rolesString = new ArrayList<>();
            entity.getRoles().forEach(role -> {
                rolesString.add(role.getName().toString());
            });
            return rolesString;
        } else {
            return null;
        }
    }

}
