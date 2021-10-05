package com.cuongnghiem.springpetclinic.repository;

import com.cuongnghiem.springpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by cuongnghiem on 05/10/2021
 **/

public interface VetRepository extends CrudRepository<Vet, Long> {
}
