package com.example.kairo.listoflistsback.service.user;


import com.example.kairo.listoflistsback.dto.AppUserDTO;
import com.example.kairo.listoflistsback.entity.AppUser;
import com.example.kairo.listoflistsback.entity.Lista;
import com.example.kairo.listoflistsback.filter.UserFilter;
import com.example.kairo.listoflistsback.framework.dto.InfoDTO;
import com.example.kairo.listoflistsback.framework.exception.CustomException;
import com.example.kairo.listoflistsback.framework.repository.JpaCrudRepository;
import com.example.kairo.listoflistsback.framework.service.JpaCrudServiceImpl;
import com.example.kairo.listoflistsback.mapper.MapperAppUser;
import com.example.kairo.listoflistsback.repository.AppUserRepository;
import com.example.kairo.listoflistsback.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl extends JpaCrudServiceImpl<AppUser, Long, UserFilter> implements AppUserService {

    private final MapperAppUser mapperAppUser;
    private final AppUserRepository repository;


    @Override
    protected JpaRepository<AppUser, Long> getRepository() {
        return repository;
    }

    @Override
    protected JpaCrudRepository<AppUser, UserFilter> getData() {
        return null;
    }

    @Override
    public InfoDTO<Long> saveAppUser(AppUserDTO dto) {

        var entity = mapperAppUser.toEntity(dto);
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

    private Long create(AppUser entity) {
        log.debug("AppUserServiceImpl.create");
        var model = save(entity);
        entity.setId(model.getId());
        return entity.getId();
    }

    private Long update(AppUser newEntity) {
        log.debug("AppUserServiceImpl.update {}", newEntity.getId());
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
