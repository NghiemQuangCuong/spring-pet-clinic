package com.cuongnghiem.springpetclinic.services.springdatajpa;

import com.cuongnghiem.springpetclinic.model.Specialty;
import com.cuongnghiem.springpetclinic.repository.SpecialtyRepository;
import com.cuongnghiem.springpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 05/10/2021
 **/
@Service
@Profile("springdatajpa")
public class SpecialtyServiceSDJpa extends AbstractServiceSDJpa<Specialty, SpecialtyRepository> implements SpecialtyService {
    public SpecialtyServiceSDJpa(SpecialtyRepository repository) {
        super(repository);
    }
}
