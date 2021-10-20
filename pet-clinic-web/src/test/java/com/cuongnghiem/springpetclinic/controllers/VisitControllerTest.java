package com.cuongnghiem.springpetclinic.controllers;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.model.Pet;
import com.cuongnghiem.springpetclinic.model.PetType;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import com.cuongnghiem.springpetclinic.services.PetService;
import com.cuongnghiem.springpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {
    @InjectMocks
    VisitController visitController;
    @Mock
    OwnerService ownerService;
    @Mock
    PetService petService;
    @Mock
    VisitService visitService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void getNewVisitView() throws Exception {
        Owner owner = Owner.builder().id(1L).build();
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().owner(owner).petType(new PetType()).build());

        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/visits/newOrUpdateVisit"))
                .andExpect(model().attributeExists("visit"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void addNewVisitSuccess() throws Exception {
        Owner owner = Owner.builder().id(1L).build();
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().owner(owner).petType(new PetType()).build());

        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("date", "2099-10-10")
                        .param("description", "Some description"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));

        verify(visitService).save(any());
    }

    @Test
    void addNewVisitFail() throws Exception {
        Owner owner = Owner.builder().id(1L).build();
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petService.findById(anyLong())).thenReturn(Pet.builder().owner(owner).petType(new PetType()).build());

        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("date", "2010-10-10")
                        .param("description", "Some description"))
                .andExpect(status().isOk())
                .andExpect(view().name("/visits/newOrUpdateVisit"));

        verify(visitService, times(0)).save(any());
    }
}