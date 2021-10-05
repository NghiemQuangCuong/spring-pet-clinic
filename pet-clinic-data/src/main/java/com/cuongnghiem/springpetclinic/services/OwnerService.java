package com.cuongnghiem.springpetclinic.services;

import com.cuongnghiem.springpetclinic.model.Owner;

import java.util.Set;

/**
 * Created by cuongnghiem on 04/09/2021
 **/

public interface OwnerService extends CrudService<Owner, Long>{
    Set<Owner> findAllByLastName(String lastName);
}
