package com.cuongnghiem.springpetclinic.repository;

import com.cuongnghiem.springpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cuongnghiem on 05/10/2021
 **/

public interface PetRepository extends CrudRepository<Pet, Long> {
}
