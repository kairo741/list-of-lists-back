package com.example.kairo.listoflistsback.controller;


import com.example.kairo.listoflistsback.dto.UserDTO;
import com.example.kairo.listoflistsback.framework.dto.InfoDTO;
import com.example.kairo.listoflistsback.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController extends BaseController {

//    private final UserService userService;

//    @PostMapping("/create")
//    public ResponseEntity<InfoDTO> create(@RequestBody UserDTO dto) {
//        try {
//            userService.registerUser(dto);
//
//            var info = InfoDTO.builder().message("Usuário criado!").success(true).build();
//
//            return ResponseEntity.ok(info);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//    }
}
