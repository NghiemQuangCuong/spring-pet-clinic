package com.cuongnghiem.springpetclinic.services.springdatajpa;

import com.cuongnghiem.springpetclinic.model.PetType;
import com.cuongnghiem.springpetclinic.repository.PetTypeRepository;
import com.cuongnghiem.springpetclinic.services.PetTypeService;

/**
 * Created by cuongnghiem on 05/10/2021
 **/

public class PetTypeServiceSDJpa extends AbstractServiceSDJpa<PetType, PetTypeRepository> implements PetTypeService {
    public PetTypeServiceSDJpa(PetTypeRepository repository) {
        super(repository);
    }
}
