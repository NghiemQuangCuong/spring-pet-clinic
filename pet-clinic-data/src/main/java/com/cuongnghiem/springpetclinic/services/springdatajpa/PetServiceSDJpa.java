package com.cuongnghiem.springpetclinic.services.springdatajpa;

import com.cuongnghiem.springpetclinic.model.Pet;
import com.cuongnghiem.springpetclinic.repository.PetRepository;
import com.cuongnghiem.springpetclinic.services.PetService;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 05/10/2021
 **/
@Service
public class PetServiceSDJpa extends AbstractServiceSDJpa<Pet, PetRepository> implements PetService {
    public PetServiceSDJpa(PetRepository repository) {
        super(repository);
    }
}
