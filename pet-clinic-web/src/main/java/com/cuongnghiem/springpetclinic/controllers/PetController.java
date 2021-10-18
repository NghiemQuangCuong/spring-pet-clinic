package com.cuongnghiem.springpetclinic.controllers;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.model.Pet;
import com.cuongnghiem.springpetclinic.model.PetType;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import com.cuongnghiem.springpetclinic.services.PetService;
import com.cuongnghiem.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by cuongnghiem on 18/10/2021
 **/
@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    public static final String NEW_OR_UPDATE_PET_VIEW_NAME = "/pets/newOrUpdatePet";

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService, OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("petType")
    public Set<PetType> populatePetType(){
        return petTypeService.findAll();
    }

    @GetMapping("/pets/new")
    public String getAddNewPetView(@PathVariable Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);
        if (owner == null)
            throw new RuntimeException("Cannot find owner");

        Pet pet = new Pet();

        Owner owner1 = new Owner();
        owner1.setFirstName(owner.getFirstName());
        owner1.setLastName(owner.getLastName());

        pet.setOwner(owner1);
        model.addAttribute("pet", pet);
        return NEW_OR_UPDATE_PET_VIEW_NAME;
    }

    @PostMapping("/pets/new")
    public String addNewPet(@PathVariable Long ownerId, @ModelAttribute Pet pet) {
        pet.setOwner(ownerService.findById(ownerId));
        petService.save(pet);
        return "redirect:/owners/" + pet.getOwner().getId();
    }

    @GetMapping("/pets/{petId}/edit")
    public String getEditPetView(@PathVariable Long ownerId, @PathVariable Long petId, Model model) {
        Pet pet = petService.findById(petId);
        Owner owner = ownerService.findById(ownerId);
        if (pet == null)
            throw new RuntimeException("Cannot find pet id = " + petId);

        Owner owner1 = Owner.builder().firstName(owner.getFirstName())
                .lastName(owner.getLastName()).build();
        pet.setOwner(owner1);
        model.addAttribute("pet", pet);
        return NEW_OR_UPDATE_PET_VIEW_NAME;
    }

    @PostMapping("/pets/{petId}/edit")
    public String addPet(@PathVariable Long ownerId, @ModelAttribute Pet pet) {
        Owner owner = ownerService.findById(ownerId);
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }
}
