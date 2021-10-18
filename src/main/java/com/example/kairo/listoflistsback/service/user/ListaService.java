package com.example.kairo.listoflistsback.service.user;


import com.example.kairo.listoflistsback.dto.ListaDTO;
import com.example.kairo.listoflistsback.entity.Lista;
import com.example.kairo.listoflistsback.framework.dto.InfoDTO;

import java.util.List;


public interface ListaService {

    InfoDTO<Long> saveLista(ListaDTO dto);

    InfoDTO deleteLista(Long id);

    InfoDTO<List<Lista>> findAllListas();

}
