package com.cuongnghiem.springpetclinic.services.springdatajpa;

import com.cuongnghiem.springpetclinic.model.Vet;
import com.cuongnghiem.springpetclinic.repository.VetRepository;
import com.cuongnghiem.springpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 05/10/2021
 **/
@Service
@Profile("springdatajpa")
public class VetServiceSDJpa extends AbstractServiceSDJpa<Vet, VetRepository> implements VetService {
    public VetServiceSDJpa(VetRepository repository) {
        super(repository);
    }
}
