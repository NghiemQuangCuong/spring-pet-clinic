package com.cuongnghiem.springpetclinic.services.springdatajpa;

import com.cuongnghiem.springpetclinic.model.Specialty;
import com.cuongnghiem.springpetclinic.repository.SpecialtyRepository;
import com.cuongnghiem.springpetclinic.services.SpecialtyService;

/**
 * Created by cuongnghiem on 05/10/2021
 **/

public class SpecialtyServiceSDJpa extends AbstractServiceSDJpa<Specialty, SpecialtyRepository> implements SpecialtyService {
    public SpecialtyServiceSDJpa(SpecialtyRepository repository) {
        super(repository);
    }
}
