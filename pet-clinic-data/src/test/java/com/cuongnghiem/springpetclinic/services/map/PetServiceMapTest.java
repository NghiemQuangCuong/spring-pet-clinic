package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PetServiceMapTest {
    private PetServiceMap petServiceMap = new PetServiceMap();

    @BeforeEach
    void setUp() {
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();
        Pet pet3 = new Pet();
        petServiceMap.save(pet1);
        petServiceMap.save(pet2);
        petServiceMap.save(pet3);
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