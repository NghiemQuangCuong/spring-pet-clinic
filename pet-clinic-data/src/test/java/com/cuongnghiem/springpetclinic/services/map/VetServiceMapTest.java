package com.cuongnghiem.springpetclinic.services.map;

import com.cuongnghiem.springpetclinic.model.Specialty;
import com.cuongnghiem.springpetclinic.model.Vet;
import com.cuongnghiem.springpetclinic.services.SpecialtyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class VetServiceMapTest {

    private VetServiceMap vetServiceMap;

    private Vet vet;

    @Mock
    SpecialtyService specialtyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        vetServiceMap = new VetServiceMap(specialtyService);
        vet = new Vet();
    }

    @Test
    void save() {
        // given
        Set<Specialty> specialties = new HashSet<>();
        Specialty specialty0 = new Specialty();
        specialties.add(specialty0);
        Specialty specialty1 = new Specialty();
        specialty1.setId(5L);
        specialty1.setDescription("Specialty 1");
        specialties.add(specialty1);
        vet.setSpecialties(specialties);

        ArgumentCaptor<Specialty> specialtyArgumentCaptor
                = ArgumentCaptor.forClass(Specialty.class);

        // when
        Vet vetSaveReturn = vetServiceMap.save(vet);

        // then
        assertNotNull(vet.getId());
        assertEquals(vet, vetSaveReturn);
        verify(specialtyService, times(1))
                .save(specialtyArgumentCaptor.capture());
        assertEquals(specialty0, specialtyArgumentCaptor.getValue());
    }
}