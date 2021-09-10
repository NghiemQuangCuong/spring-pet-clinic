package com.cuongnghiem.springpetclinic.services;

import java.util.Set;

/**
 * Created by cuongnghiem on 10/09/2021
 **/

public interface CrudService<T, ID> {
    T findById(ID id);

    Set<T> findAll();

    T save(ID id, T object);

    void deleteById(ID id);

    void delete(T object);


}
