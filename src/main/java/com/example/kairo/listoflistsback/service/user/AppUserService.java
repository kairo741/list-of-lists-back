package com.example.kairo.listoflistsback.service.user;


import com.example.kairo.listoflistsback.dto.AppUserDTO;
import com.example.kairo.listoflistsback.framework.dto.InfoDTO;


public interface AppUserService {

    InfoDTO<Long> saveAppUser(AppUserDTO dto);
}
