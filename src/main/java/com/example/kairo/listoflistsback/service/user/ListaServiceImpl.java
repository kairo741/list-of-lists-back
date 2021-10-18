package com.example.kairo.listoflistsback.service.user;


import com.example.kairo.listoflistsback.dto.ListaDTO;
import com.example.kairo.listoflistsback.entity.Lista;
import com.example.kairo.listoflistsback.filter.UserFilter;
import com.example.kairo.listoflistsback.framework.dto.InfoDTO;
import com.example.kairo.listoflistsback.framework.exception.CustomException;
import com.example.kairo.listoflistsback.framework.repository.JpaCrudRepository;
import com.example.kairo.listoflistsback.framework.service.JpaCrudServiceImpl;
import com.example.kairo.listoflistsback.mapper.MapperLista;
import com.example.kairo.listoflistsback.repository.ListaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListaServiceImpl extends JpaCrudServiceImpl<Lista, Long, UserFilter> implements ListaService {

    private final MapperLista mapperLista;

    private final ListaRepository repository;

    @Override
    protected JpaRepository<Lista, Long> getRepository() {
        return repository;
    }

    @Override
    protected JpaCrudRepository<Lista, UserFilter> getData() {
        return null;
    }

    @Override
    public InfoDTO<Long> saveLista(ListaDTO dto) {

        var entity = mapperLista.toEntity(dto);
        Long id;
        try {
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


    private Long create(Lista entity) {
        log.debug("ListaServiceImpl.create");
        var model = save(entity);
        entity.setId(model.getId());
        return entity.getId();
    }

    private Long update(Lista newEntity) {
        log.debug("ListaServiceImpl.update {}", newEntity.getId());
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
