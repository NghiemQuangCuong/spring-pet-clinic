package com.cuongnghiem.springpetclinic.controllers;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.model.Pet;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import com.cuongnghiem.springpetclinic.services.PetService;
import com.cuongnghiem.springpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {
    @InjectMocks
    PetController petController;
    @Mock
    PetService petService;
    @Mock
    OwnerService ownerService;
    @Mock
    PetTypeService petTypeService;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
    }

    @Test
    void getAddNewPetView() throws Exception {

        when(ownerService.findById(anyLong())).thenReturn(new Owner());

        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/pets/newOrUpdatePet"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void addNewPetSuccess() throws Exception{
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/1/pets/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Some name")
                        .param("birthDay", "2019-10-10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void getEditPetView() throws Exception{

        when(petService.findById(anyLong())).thenReturn(new Pet());
        when(ownerService.findById(anyLong())).thenReturn(new Owner());

        mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("/pets/newOrUpdatePet"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void editPetSuccess() throws Exception{

        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/1/pets/1/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Some name")
                        .param("birthDay", "2019-10-10"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void editPetFail() throws Exception{
        mockMvc.perform(post("/owners/1/pets/1/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "")
                        .param("birthDay", "2099-10-10"))
                .andExpect(status().isOk())
                .andExpect(view().name("/pets/newOrUpdatePet"));
    }
}