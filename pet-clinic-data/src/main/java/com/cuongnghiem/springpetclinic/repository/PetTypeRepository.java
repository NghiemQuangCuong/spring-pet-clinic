package com.cuongnghiem.springpetclinic.repository;

import com.cuongnghiem.springpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cuongnghiem on 05/10/2021
 **/

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
