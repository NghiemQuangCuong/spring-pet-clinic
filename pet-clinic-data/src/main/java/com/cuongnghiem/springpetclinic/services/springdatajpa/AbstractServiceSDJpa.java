package com.cuongnghiem.springpetclinic.services.springdatajpa;

import com.cuongnghiem.springpetclinic.services.CrudService;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by cuongnghiem on 05/10/2021
 **/

public class AbstractServiceSDJpa<T, R extends CrudRepository<T, Long>> implements CrudService<T, Long> {

    protected final R repository;

    public AbstractServiceSDJpa(R repository) {
        this.repository = repository;
    }

    @Override
    public T findById(Long aLong) {
        return repository.findById(aLong).orElse(null);
    }

    @Override
    public Set<T> findAll() {
        Set<T> result = new HashSet<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public T save(T object) {
        return repository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        repository.deleteById(aLong);
    }

    @Override
    public void delete(T object) {
        repository.delete(object);
    }
}
