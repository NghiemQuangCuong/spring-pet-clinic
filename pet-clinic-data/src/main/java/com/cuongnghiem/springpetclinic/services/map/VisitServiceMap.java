package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Visit;
import com.cuongnghiem.springpetclinic.services.PetService;
import com.cuongnghiem.springpetclinic.services.VisitService;
import org.springframework.stereotype.Service;

/**
 * Created by cuongnghiem on 05/10/2021
 **/
@Service
public class VisitServiceMap extends AbstractMapService<Visit, Long> implements VisitService {

    private final PetService petService;

    public VisitServiceMap(PetService petService) {
        this.petService = petService;
    }

    @Override
    public Visit save(Visit object) {
        if (object != null) {
            // we want to make sure specialty of vet object will all available in map persistance.
            // if not, we will save that.
            if (object.getPet() != null && object.getPet().getId() == null) {
                petService.save(object.getPet());
            }

            Long nextId = super.getNextId();
            map.put(nextId, object);
            object.setId(nextId);
        }
        else
            throw new RuntimeException("Object must not be null");

        return object;
    }
}
