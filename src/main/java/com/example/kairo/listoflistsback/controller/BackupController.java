package com.example.kairo.listoflistsback.controller;


import com.example.kairo.listoflistsback.dto.AppUserDTO;
import com.example.kairo.listoflistsback.dto.ItemDTO;
import com.example.kairo.listoflistsback.dto.ListaDTO;
import com.example.kairo.listoflistsback.framework.dto.InfoDTO;
import com.example.kairo.listoflistsback.service.user.AppUserService;
import com.example.kairo.listoflistsback.service.user.ItemService;
import com.example.kairo.listoflistsback.service.user.ListaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/backup")
@RequiredArgsConstructor
@Slf4j
public class BackupController extends BaseController {


    private final AppUserService appUserService;
    private final ListaService listaService;
    private final ItemService itemService;

    @PostMapping("/save-user")
    public ResponseEntity<InfoDTO<Long>> saveAppUser(@RequestBody AppUserDTO dto) {
        try {
            // Retorna o InfoDTO com o Id gerado para o Item
            var info = appUserService.saveAppUser(dto);

            return ResponseEntity.ok(info);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/save-lista")
    public ResponseEntity<InfoDTO<Long>> saveLista(@RequestBody ListaDTO dto) {
        try {
            // Retorna o InfoDTO com o Id gerado para o Item
            var info = listaService.saveLista(dto);

            return ResponseEntity.ok(info);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @PostMapping("/save-item")
    public ResponseEntity<InfoDTO<Long>> saveItem(@RequestBody ItemDTO dto) {
        try {

            // Retorna o InfoDTO com o Id gerado para o Item
            var info = itemService.saveItem(dto);


            return ResponseEntity.ok(info);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
