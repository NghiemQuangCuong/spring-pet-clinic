package com.cuongnghiem.springpetclinic.services.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by cuongnghiem on 10/09/2021
 **/

public abstract class AbstractMapService<T, ID> {
    protected Map<ID, T> map = new HashMap<>();

    protected T findById(ID id){
        return map.get(id);
    }

    protected Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    protected T save(ID id, T object){
        map.put(id, object);
        return object;
    }

    protected void deleteById(ID id){
        map.remove(id);
    }

    protected void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }
}
