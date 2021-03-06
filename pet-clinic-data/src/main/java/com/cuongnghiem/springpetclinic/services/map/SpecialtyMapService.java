package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Specialty;
import com.cuongnghiem.springpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 25/09/2021
 **/
@Service
@Profile({"default", "map"})
public class SpecialtyMapService extends AbstractMapService<Specialty, Long> implements SpecialtyService {
}
