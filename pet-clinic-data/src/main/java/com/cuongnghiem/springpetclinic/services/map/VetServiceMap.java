package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Vet;
import com.cuongnghiem.springpetclinic.services.VetService;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 10/09/2021
 **/
@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

}
