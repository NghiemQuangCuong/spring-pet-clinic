package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Pet;
import com.cuongnghiem.springpetclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 10/09/2021
 **/
@Service
@Profile({"default", "map"})
public class PetServiceMap extends AbstractMapService<Pet, Long> implements PetService {

}
