package com.example.kairo.listoflistsback.service.user;


import com.example.kairo.listoflistsback.dto.ItemDTO;
import com.example.kairo.listoflistsback.entity.Item;
import com.example.kairo.listoflistsback.filter.UserFilter;
import com.example.kairo.listoflistsback.framework.dto.InfoDTO;
import com.example.kairo.listoflistsback.framework.exception.CustomException;
import com.example.kairo.listoflistsback.framework.repository.JpaCrudRepository;
import com.example.kairo.listoflistsback.framework.service.JpaCrudServiceImpl;
import com.example.kairo.listoflistsback.mapper.MapperItem;
import com.example.kairo.listoflistsback.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl extends JpaCrudServiceImpl<Item, Long, UserFilter> implements ItemService {

    private final MapperItem mapperItem;

    private final ItemRepository repository;


    @Override
    protected JpaRepository<Item, Long> getRepository() {
        return repository;
    }

    @Override
    protected JpaCrudRepository<Item, UserFilter> getData() {
        return null;
    }

    @Override
    public InfoDTO<Long> saveItem(ItemDTO dto) {

        var entity = mapperItem.toEntity(dto);
        Long id;
        try {
//            var dbEntity = repository.findByItem(entity);

            if (entity.getId() != null && entity.getId() > 0) {
                id = update(entity);
            } else {
                id = create(entity);
            }
            return InfoDTO.<Long>builder().success(true).object(id).build();
        } catch (Exception e) {
            return InfoDTO.<Long>builder().success(false).object(null).build();
        }

    }


    private Long create(Item entity) {
        log.debug("ItemServiceImpl.create");
        var model = save(entity);
        entity.setId(model.getId());
        return entity.getId();
    }

    private Long update(Item newEntity) {
        log.debug("ItemServiceImpl.update {}", newEntity.getId());
        var op = findById(newEntity.getId());

        if (op.isPresent()) {
            var model = save(newEntity);
            return model.getId();
        } else {
            return create(newEntity);
//            throw new CustomException("Nenhum item encontrado");
        }
    }
}
