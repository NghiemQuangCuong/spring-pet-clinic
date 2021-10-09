package com.cuongnghiem.springpetclinic.services.springdatajpa;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.repository.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerServiceSDJpaTest {

    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerServiceSDJpa ownerServiceSDJpa;

    @BeforeEach
    void setUp() {
    }

    @Test
    void findById() {
        Long ownerId = 1L;
        when(ownerRepository.findById(ownerId)).thenReturn(Optional.of(Owner.builder().id(ownerId).build()));

        Owner owner = ownerServiceSDJpa.findById(ownerId);

        assertEquals(ownerId, owner.getId());
        verify(ownerRepository, times(1)).findById(ownerId);
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(owners);

        Set<Owner> ownerResult = ownerServiceSDJpa.findAll();

        assertEquals(owners.size(), ownerResult.size());
        verify(ownerRepository, times(1)).findAll();
    }

    @Test
    void save() {
        Owner owner = Owner.builder().build();

        when(ownerRepository.save(owner)).thenReturn(owner);

        Owner ownerResult = ownerServiceSDJpa.save(owner);

        assertEquals(owner, ownerResult);
        verify(ownerRepository, times(1)).save(owner);
    }

    @Test
    void deleteById() {
        ownerServiceSDJpa.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(1L);
    }

    @Test
    void delete() {
        Owner owner = Owner.builder().build();
        ownerServiceSDJpa.delete(owner);

        verify(ownerRepository, times(1)).delete(owner);
    }

    @Test
    void findAllByLastName() {
        String lastName = "Nghiem";
        ownerServiceSDJpa.findAllByLastName(lastName);

        verify(ownerRepository, times(1)).findAllByLastName(lastName);
    }
}