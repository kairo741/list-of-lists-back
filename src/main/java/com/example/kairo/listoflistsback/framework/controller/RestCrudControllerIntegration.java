package com.example.kairo.listoflistsback.framework.controller;

import com.example.kairo.listoflistsback.framework.service.JpaCrudService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class RestCrudControllerIntegration<T, ID extends Serializable, Y> {
    protected abstract JpaCrudService<T, ID, Y> getService();

    @GetMapping
    public List<T> findAll() {
        return getService().findAll();
    }

    @PostMapping("page")
    public Page<?> findAll(@RequestParam int page,
                           @RequestParam int size,
                           @RequestParam(required = false) String order,
                           @RequestParam(required = false) Boolean asc,
                           @RequestParam(required = false) String query,
                           @RequestBody(required = false) Y filter) {
        PageRequest pageRequest = PageRequest.of(page, size);

        if (order != null && asc != null) {
            pageRequest = PageRequest.of(page, size, asc ? Sort.Direction.ASC : Sort.Direction.DESC, order);
        }

        if (query != null || filter != null) {
            return getService().findAll(pageRequest, query, filter);
        } else {
            return getService().findAll(pageRequest);
        }
    }

    @GetMapping("{id}")
    public T findOne(@PathVariable ID id) {
        T entity = getService().findOne(id);
        if (entity != null) {
            postFindOneWhenExists(entity);
        }
        return entity;
    }

    public void postFindOneWhenExists(T entity) {
    }

    @PutMapping
    public <S extends T> S alterar(@Valid @RequestBody S entity) {
        return novo(entity);
    }

    public void postSave(T entity) {
    }

    @PostMapping
    public <S extends T> S novo(@Valid @RequestBody S entity) {
        entity = getService().save(entity);
        postSave(entity);
        return entity;


    }

    @GetMapping("exists/{id}")
    public boolean exists(@PathVariable ID id) {
        return getService().exists(id);
    }

    @GetMapping("count")
    public long count() {
        return getService().count();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable ID id) {
        Optional<T> entityOptional = getService().findById(id);

        if (entityOptional.isPresent()) {
            try {
                T entity = entityOptional.get();

                getService().delete(entity);

            } catch (Exception e) {
                if (e instanceof DataIntegrityViolationException) {
                    //todo tratamento de erro
                    throw new RuntimeException();
                    //throw new WarningException("Não foi possível remover, pois está sendo utilizado por outra funcionalidade");
                }
                //throw new TratamentoException("Ocorreu um erro ao remover");
            }
        }
    }
}
