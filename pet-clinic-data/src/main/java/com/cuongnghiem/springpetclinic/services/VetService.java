package com.cuongnghiem.springpetclinic.services;

import com.cuongnghiem.springpetclinic.model.Vet;

import java.util.Set;

/**
 * Created by cuongnghiem on 04/09/2021
 **/

public interface VetService {
    Vet findById(Long id);

    Vet save(Vet pet);

    Set<Vet> findAll();
}
