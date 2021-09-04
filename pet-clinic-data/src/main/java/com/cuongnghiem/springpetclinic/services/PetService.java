package com.cuongnghiem.springpetclinic.services;

import com.cuongnghiem.springpetclinic.model.Pet;

import java.util.Set;

/**
 * Created by cuongnghiem on 04/09/2021
 **/

public interface PetService {
    Pet findById(Long id);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
