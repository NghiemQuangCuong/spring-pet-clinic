package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cuongnghiem on 10/09/2021
 **/
@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Set<Owner> findByLastName(String lastName) {
        return super.map
                .values()
                .stream()
                .filter(entry -> entry.getLastName().equals(lastName))
                .collect(Collectors.toSet());
    }
}
