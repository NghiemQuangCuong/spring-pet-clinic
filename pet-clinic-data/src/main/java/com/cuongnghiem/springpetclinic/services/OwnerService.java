package com.cuongnghiem.springpetclinic.services;

import com.cuongnghiem.springpetclinic.model.Owner;

import java.util.Set;

/**
 * Created by cuongnghiem on 04/09/2021
 **/

public interface OwnerService {
    Set<Owner> findByLastName(String lastName);

    Owner findById(Long id);

    Owner save(Owner owner);

    Set<Owner> findAll();
}
