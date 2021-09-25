package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Vet;
import com.cuongnghiem.springpetclinic.services.SpecialtyService;
import com.cuongnghiem.springpetclinic.services.VetService;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 10/09/2021
 **/
@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet save(Vet object) {
        if (object != null) {
            Long nextId = super.getNextId();
            map.put(nextId, object);
            object.setId(nextId);

            object.getSpecialties().forEach(specialty -> {
                if (specialty != null && specialty.getId() == null) {
                    specialtyService.save(specialty);
                } else if (specialty == null) {
                    throw new RuntimeException("Specialty must not be null");
                }
            });
        }
        else
            throw new RuntimeException("Object must not be null");

        return object;
    }
}
