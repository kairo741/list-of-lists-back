package com.example.kairo.listoflistsback.service.user;


import com.example.kairo.listoflistsback.dto.ItemDTO;
import com.example.kairo.listoflistsback.entity.Item;
import com.example.kairo.listoflistsback.framework.dto.InfoDTO;

import java.util.List;


public interface ItemService {

    InfoDTO<Long> saveItem(ItemDTO dto);

    InfoDTO deleteItem(Long id);

    InfoDTO<List<Item>> findAllItens();


}
