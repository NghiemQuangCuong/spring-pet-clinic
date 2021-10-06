package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.PetType;
import com.cuongnghiem.springpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 25/09/2021
 **/
@Service
@Profile({"default", "map"})
public class PetTypeServiceMap extends AbstractMapService<PetType, Long> implements PetTypeService {
}
