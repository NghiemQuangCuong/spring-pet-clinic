package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.services.OwnerService;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cuongnghiem on 10/09/2021
 **/

public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner save(Long id, Owner object) {
        return super.save(id, object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Set<Owner> findByLastName(String lastName) {
        return super.map
                .values()
                .stream()
                .filter(entry -> entry.getLastName().equals(lastName))
                .collect(Collectors.toSet());
    }
}
