package com.cuongnghiem.springpetclinic.services.springdatajpa;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.repository.OwnerRepository;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by cuongnghiem on 05/10/2021
 **/
@Service
@Profile("springdatajpa")
public class OwnerServiceSDJpa extends AbstractServiceSDJpa<Owner, OwnerRepository> implements OwnerService {

    public OwnerServiceSDJpa(OwnerRepository repository) {
        super(repository);
    }

    @Override
    public Set<Owner> findAllByLastName(String lastName) {
        return repository.findAllByLastName(lastName);
    }
}
