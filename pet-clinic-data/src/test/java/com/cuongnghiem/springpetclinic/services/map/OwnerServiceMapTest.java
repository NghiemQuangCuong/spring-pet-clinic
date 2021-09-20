package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OwnerServiceMapTest {

    private final OwnerServiceMap ownerServiceMap = new OwnerServiceMap();

    @BeforeEach
    void setUp() {
        Owner owner1 = new Owner();
        owner1.setFirstName("Cuong");
        owner1.setLastName("Nghiem");
        Owner owner2 = new Owner();
        owner2.setFirstName("Quang");
        owner2.setLastName("Nghiem");
        Owner owner3 = new Owner();
        owner3.setFirstName("Anh");
        owner3.setLastName("Tran");
        ownerServiceMap.save(owner1);
        ownerServiceMap.save(owner2);
        ownerServiceMap.save(owner3);
    }

    @Test
    void findById() {
        System.out.println("--- findByIdTest ---");
        System.out.println("Owner1: " + ownerServiceMap.findById(1L).getFirstName());
        System.out.println("Owner2: " + ownerServiceMap.findById(2L).getFirstName());
        System.out.println("Owner3: " + ownerServiceMap.findById(3L).getFirstName());
    }

    @Test
    void findAll() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void delete() {
    }

    @Test
    void findByLastName() {
        System.out.println("--- findByLastName ---");
        ownerServiceMap.findByLastName("Nghiem")
                .forEach(owner -> System.out.println(owner.getFirstName()));
    }
}