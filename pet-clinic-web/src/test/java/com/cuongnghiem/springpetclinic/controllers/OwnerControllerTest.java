package com.cuongnghiem.springpetclinic.controllers;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @InjectMocks
    OwnerController controller;
    @Mock
    OwnerService ownerService;

    Set<Owner> owners;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
        owners.add(Owner.builder().id(3L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void showOwnersNotFound() throws Exception{
        when(ownerService.findAllByLastName("SomeThingThatWillNotFound")).thenReturn(new HashSet<>());

        mockMvc.perform(get("/owners")
                        .param("lastName", "SomeThingThatWillNotFound"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/find"));

    }

    @Test
    void showOwnersFoundOnlyOne() throws Exception{
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());

        when(ownerService.findAllByLastName("SomeThingThatWillFoundOnlyOne")).thenReturn(owners);

        mockMvc.perform(get("/owners")
                        .param("lastName", "SomeThingThatWillFoundOnlyOne"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void showOwnersDefault() throws Exception{
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/index"))
                .andExpect(model().attribute("owners", hasSize(3)));

        verify(ownerService).findAll();
    }

    @Test
    void showOwner() throws Exception{

        when(ownerService.findById(anyLong())).thenReturn(new Owner());

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/details"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void getNewOwnerView() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/newOrUpdateOwner"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void addNewOwner() throws Exception {
        mockMvc.perform(post("/owners/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners"));

        verify(ownerService).save(any());
    }

    @Test
    void getOwnerEditView() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(new Owner());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/newOrUpdateOwner"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void editOwner() throws Exception{
        mockMvc.perform(post("/owners/1/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners"));

        verify(ownerService).save(any());
    }
}