package com.cuongnghiem.springpetclinic.repository;

import com.cuongnghiem.springpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by cuongnghiem on 05/10/2021
 **/

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Set<Owner> findAllByLastNameLike(String lastName);
    Set<Owner> findAllByLastNameContains(String lastName);
}
