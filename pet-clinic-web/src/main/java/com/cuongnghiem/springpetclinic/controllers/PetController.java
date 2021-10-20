package com.cuongnghiem.springpetclinic.controllers;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.model.Pet;
import com.cuongnghiem.springpetclinic.model.PetType;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import com.cuongnghiem.springpetclinic.services.PetService;
import com.cuongnghiem.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        pet.setOwner(Owner.builder().firstName(owner.getFirstName()).lastName(owner.getLastName()).build());
        model.addAttribute("pet", pet);
        return NEW_OR_UPDATE_PET_VIEW_NAME;
    }

    @PostMapping("/pets/new")
    public String addNewPet(@PathVariable Long ownerId, @Valid @ModelAttribute("pet") Pet pet, BindingResult result) {
        if (result.hasErrors())
            return NEW_OR_UPDATE_PET_VIEW_NAME;

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

        pet.setOwner(Owner.builder().firstName(owner.getFirstName()).lastName(owner.getLastName()).build());
        model.addAttribute("pet", pet);
        return NEW_OR_UPDATE_PET_VIEW_NAME;
    }

    @PostMapping("/pets/{petId}/edit")
    public String addPet(@PathVariable Long ownerId, @Valid @ModelAttribute("pet") Pet pet, BindingResult result) {
        if (result.hasErrors())
            return NEW_OR_UPDATE_PET_VIEW_NAME;

        Owner owner = ownerService.findById(ownerId);
        pet.setOwner(owner);
        petService.save(pet);
        return "redirect:/owners/" + owner.getId();
    }
}
