package com.example.kairo.listoflistsback.framework.service;

import com.example.kairo.listoflistsback.framework.repository.JpaCrudRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public abstract class JpaCrudServiceImpl<T, ID extends Serializable, Y> implements JpaCrudService<T, ID, Y> {

    protected abstract JpaRepository<T, ID> getRepository();

    protected abstract JpaCrudRepository<T, Y> getData();

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        List<T> list = getRepository().findAll();
        postFindAll(list);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll(Sort sort) {
        List<T> list = getRepository().findAll(sort);
        postFindAll(list);
        return list;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        Page<T> page = getRepository().findAll(pageable);
        postFindAll(page);
        return page;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable, String query, Y filter) {
        Page<T> page = getData().pageByFilter(pageable, filter);
        postFindAll(page);
        return page;
    }

    @Override
    @Transactional
    public void flush() {
        getRepository().flush();
    }

    @Override
    @Transactional(readOnly = true)
    public T findOne(ID id) {
        preFindOne(id);
        T entity = getRepository().findById(id).orElse(null);
        postFindOne(id);

        if (entity != null)
            postFindOne(entity);

        return entity;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        preFindOne(id);
        return getRepository().findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean exists(ID id) {
        return getRepository().existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return getRepository().count();
    }

    @Override
    @Transactional
    public void delete(ID id) {
        Optional<T> entity = findById(id);

        if (entity.isPresent()) {
            preDelete(id);
            preDelete(entity.get());
            getRepository().deleteById(id);
            postDelete(id);
            postDelete(entity.get());
        }
    }

    @Override
    @Transactional
    public void delete(T entity) {
        preDelete(entity);
        getRepository().delete(entity);
        postDelete(entity);
    }

    @Override
    @Transactional
    public void delete(Iterable<? extends T> iterable) {
        iterable.forEach(this::preDelete);
        getRepository().deleteAll(iterable);
        iterable.forEach(this::postDelete);
    }

    @Override
    @Transactional
    public void deleteAll() {
        getRepository().deleteAll();
    }

    @Override
    @Transactional
    public Iterable<T> save(Iterable<T> iterable) {
        try {
            iterable.forEach(this::preSave);
            getRepository().saveAll(iterable);
            iterable.forEach(this::postSave);
            return iterable;
        } catch (Exception e) {
            throw e;
            //todo tratamento de erros
        }
    }

    @Override
    @Transactional
    public T saveAndFlush(T entity) {
        try {
            preSave(entity);
            entity = getRepository().saveAndFlush(entity);
            postSave(entity);
            return entity;
        } catch (Exception e) {
            throw e;
            //todo tratamento de erros
        }
    }

    @Override
    @Transactional
    public <S extends T> S save(S entity) {
        try {
            preSave(entity);
            entity = getRepository().save(entity);
            postSave(entity);
            return entity;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
            //todo tratamento de erros
        }
    }


    protected void preSave(T entity) {

    }

    protected void postSave(T entity) {
    }

    protected void preDelete(ID id) {

    }

    protected void postDelete(ID id) {

    }

    protected void preDelete(T entity) {

    }

    protected void postDelete(T entity) {

    }

    private void preFindOne(ID id) {

    }

    private void postFindOne(ID id) {

    }

    protected void postFindOne(T entity) {

    }

    protected void postFindAll(Iterable<T> iterable) {

    }

    private void postComplete(Iterable<T> iterable) {

    }


}
