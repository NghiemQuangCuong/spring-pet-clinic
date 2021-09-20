package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Pet;
import com.cuongnghiem.springpetclinic.services.PetService;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 10/09/2021
 **/
@Service
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {

}
