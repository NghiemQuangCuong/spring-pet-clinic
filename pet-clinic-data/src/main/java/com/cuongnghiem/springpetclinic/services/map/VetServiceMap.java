package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Vet;
import com.cuongnghiem.springpetclinic.services.SpecialtyService;
import com.cuongnghiem.springpetclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 10/09/2021
 **/
@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialtyService specialtyService;

    public VetServiceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Vet save(Vet object) {
        if (object != null) {
            // we want to make sure specialty of vet object will all available in map persistance.
            // if not, we will save that.
            object.getSpecialties().forEach(specialty -> {
                if (specialty != null && specialty.getId() == null) {
                    specialtyService.save(specialty);
                } else if (specialty == null) {
                    throw new RuntimeException("Specialty must not be null");
                }
            });

            Long nextId = super.getNextId();
            map.put(nextId, object);
            object.setId(nextId);
        }
        else
            throw new RuntimeException("Object must not be null");

        return object;
    }
}
