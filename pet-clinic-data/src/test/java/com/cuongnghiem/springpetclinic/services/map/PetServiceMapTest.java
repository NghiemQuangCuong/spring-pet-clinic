package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PetServiceMapTest {
    private PetServiceMap petServiceMap = new PetServiceMap();

    @BeforeEach
    void setUp() {
        Pet pet1 = new Pet();
        pet1.setId(1L);
        Pet pet2 = new Pet();
        pet2.setId(2L);
        Pet pet3 = new Pet();
        pet3.setId(3L);
        petServiceMap.save(pet1.getId(), pet1);
        petServiceMap.save(pet2.getId(), pet2);
        petServiceMap.save(pet3.getId(), pet3);
    }

    @Test
    void findById() {
        petServiceMap.findById(1L);
        petServiceMap.findById(2L);
        petServiceMap.findById(3L);
    }

    @Test
    void findAll() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void save() {
    }
}