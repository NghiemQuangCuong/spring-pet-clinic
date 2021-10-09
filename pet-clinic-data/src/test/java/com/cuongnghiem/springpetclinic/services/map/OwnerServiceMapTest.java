package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.services.PetService;
import com.cuongnghiem.springpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    private OwnerServiceMap ownerServiceMap;
    private Long ownerId = 1L;
    private String lastName = "Nghiem";

    @Mock
    private PetTypeService petTypeService;
    @Mock
    private PetService petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ownerServiceMap = new OwnerServiceMap(petTypeService, petService);

        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);

        assertNotNull(owner);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void findAll() {
        ownerServiceMap.save(Owner.builder().build());

        Set<Owner> owners = ownerServiceMap.findAll();

        assertEquals(2, owners.size());
    }

    @Test
    void saveNoId() {
        Owner owner = Owner.builder().build();
        Owner ownerSaved = ownerServiceMap.save(owner);

        assertNotNull(owner.getId());
        assertEquals(owner.getId(), ownerSaved.getId());
    }

    @Test
    void saveExistingId() {
        Long ownerId = 2L;
        Owner ownerSaved = ownerServiceMap.save(Owner.builder().id(ownerId).build());

        assertEquals(ownerId, ownerSaved.getId());
    }

    @Test
    void saveExistingIdWithException() {
        Long ownerId = 1L;

        assertThrows(Exception.class, () -> {
            Owner ownerSaved = ownerServiceMap.save(Owner.builder().id(ownerId).build());
        });
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));

        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void findAllByLastName() {
        ownerServiceMap.save(Owner.builder().lastName("Tran").build());
        ownerServiceMap.save(Owner.builder().build());

        Set<Owner> owners = ownerServiceMap.findAllByLastName(lastName);

        assertNotNull(owners);
        assertEquals(1, owners.size());
    }
}