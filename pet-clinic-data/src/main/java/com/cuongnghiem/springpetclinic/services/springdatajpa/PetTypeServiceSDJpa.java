package com.cuongnghiem.springpetclinic.services.springdatajpa;

import com.cuongnghiem.springpetclinic.model.PetType;
import com.cuongnghiem.springpetclinic.repository.PetTypeRepository;
import com.cuongnghiem.springpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 05/10/2021
 **/
@Service
@Profile("springdatajpa")
public class PetTypeServiceSDJpa extends AbstractServiceSDJpa<PetType, PetTypeRepository> implements PetTypeService {
    public PetTypeServiceSDJpa(PetTypeRepository repository) {
        super(repository);
    }
}
