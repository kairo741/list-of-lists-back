package com.example.kairo.listoflistsback.service.user;


import com.example.kairo.listoflistsback.dto.UserDTO;
import com.example.kairo.listoflistsback.framework.exception.CustomException;


public interface UserService {

//    JwtResponse authenticateUser(LoginRequest loginRequest);

    void registerUser(UserDTO userDTO) throws CustomException;
}
