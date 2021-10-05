package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import com.cuongnghiem.springpetclinic.services.PetService;
import com.cuongnghiem.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cuongnghiem on 10/09/2021
 **/
@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            // we want to make sure pet and pettype of owner object will all available in map persistance.
            // if not, we will save that.
            object.getPets().forEach(pet -> {
                if (pet.getPetType() != null) {
                    if (pet.getPetType().getId() == null) {
                        petTypeService.save(pet.getPetType());
                    }

                    if (pet.getId() == null) {
                        petService.save(pet);
                    }
                } else {
                    throw new RuntimeException("Pet must have a PetType");
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

    @Override
    public Set<Owner> findAllByLastName(String lastName) {
        return super.map
                .values()
                .stream()
                .filter(entry -> entry.getLastName().equals(lastName))
                .collect(Collectors.toSet());
    }
}
