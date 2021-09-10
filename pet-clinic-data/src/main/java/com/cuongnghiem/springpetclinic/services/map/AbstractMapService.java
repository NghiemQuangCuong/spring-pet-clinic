package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.services.CrudService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by cuongnghiem on 10/09/2021
 **/

public abstract class AbstractMapService<T, ID> implements CrudService<T, ID>{
    protected Map<ID, T> map = new HashMap<>();

    public T findById(ID id){
        return map.get(id);
    }

    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    public T save(ID id, T object){
        map.put(id, object);
        return object;
    }

    public void deleteById(ID id){
        map.remove(id);
    }

    public void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
}
