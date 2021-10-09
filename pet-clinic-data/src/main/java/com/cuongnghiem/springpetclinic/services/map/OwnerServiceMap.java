package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import com.cuongnghiem.springpetclinic.services.PetService;
import com.cuongnghiem.springpetclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cuongnghiem on 10/09/2021
 **/
@Service
@Profile({"default", "map"})
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

            if (object.getId() == null) {
                Long nextId = super.getNextId();
                map.put(nextId, object);
                object.setId(nextId);
            }
            else {
                if (map.get(object.getId()) == null)
                    map.put(object.getId(), object);
                else
                    throw new RuntimeException("Object Id is not unique in map");
            }

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
                .filter(entry -> {
                    if (entry.getLastName() != null && entry.getLastName().equals(lastName))
                        return true;

                    return false;
                } )
                .collect(Collectors.toSet());
    }
}
