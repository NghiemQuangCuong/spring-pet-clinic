package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.BaseEntity;
import com.cuongnghiem.springpetclinic.services.CrudService;

import java.util.*;

/**
 * Created by cuongnghiem on 10/09/2021
 **/

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> implements CrudService<T, ID>{
    protected Map<Long, T> map = new HashMap<>();

    public T findById(ID id){
        return map.get(id);
    }

    public Set<T> findAll(){
        return new HashSet<>(map.values());
    }

    public T save(T object) {
        if (object != null) {
            Long nextId = getNextId();
            map.put(nextId, object);
            object.setId(nextId);
        }
        else
            throw new RuntimeException("Object must not be null");

        return object;
    }

    public void deleteById(ID id){
        map.remove(id);
    }

    public void delete(T object){
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId(){
        if (map.isEmpty())
            return 1L;

        return Collections.max(map.keySet()) + 1L;
    }
}
