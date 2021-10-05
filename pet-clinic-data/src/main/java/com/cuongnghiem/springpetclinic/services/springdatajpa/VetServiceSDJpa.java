package com.cuongnghiem.springpetclinic.services.springdatajpa;

import com.cuongnghiem.springpetclinic.model.Vet;
import com.cuongnghiem.springpetclinic.repository.VetRepository;
import com.cuongnghiem.springpetclinic.services.VetService;

/**
 * Created by cuongnghiem on 05/10/2021
 **/

public class VetServiceSDJpa extends AbstractServiceSDJpa<Vet, VetRepository> implements VetService {
    public VetServiceSDJpa(VetRepository repository) {
        super(repository);
    }
}
