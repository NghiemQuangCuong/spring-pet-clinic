package com.cuongnghiem.springpetclinic.controllers;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.model.Pet;
import com.cuongnghiem.springpetclinic.model.PetType;
import com.cuongnghiem.springpetclinic.model.Visit;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import com.cuongnghiem.springpetclinic.services.PetService;
import com.cuongnghiem.springpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by cuongnghiem on 19/10/2021
 **/

@Controller
@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
public class VisitController {

    public static final String NEW_OR_UPDATE_VISIT_VIEW = "/visits/newOrUpdateVisit";

    private final PetService petService;
    private final OwnerService ownerService;
    private final VisitService visitService;

    public VisitController(PetService petService, OwnerService ownerService, VisitService visitService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.visitService = visitService;
    }

    @ModelAttribute("verify")
    public void verify(@PathVariable Long petId, @PathVariable Long ownerId) {
        Pet pet = petService.findById(petId);
        Owner owner = ownerService.findById(ownerId);
        if (pet == null)
            throw new RuntimeException("Pet Not Found, id = " + petId);
        if (owner == null)
            throw new RuntimeException("Owner not found, id = " + ownerId);
        if (!pet.getOwner().getId().equals(owner.getId()))
            throw new RuntimeException("Owner id = " + ownerId + "do not have pet id " + petId);
    }

    @GetMapping("/new")
    public String getNewVisitView(@PathVariable Long petId,
                                  Model model) {
        Visit visit = new Visit();
        Pet clonePet = clonePet(petService.findById(petId));
        model.addAttribute("visit", visit);
        model.addAttribute("pet", clonePet);
        return NEW_OR_UPDATE_VISIT_VIEW;
    }

    @PostMapping("/new")
    public String addNewVisit(@PathVariable Long ownerId,
                              @PathVariable Long petId,
                              @ModelAttribute Visit visit) {
        visit.setPet(petService.findById(petId));
        visitService.save(visit);
        return "redirect:/owners/" + ownerId;
    }

    private Pet clonePet(Pet pet) {
        Pet clonePet = new Pet();
        clonePet.setBirthDay(pet.getBirthDay());
        clonePet.setName(pet.getName());
        clonePet.setVisits(pet.getVisits());
        PetType clonePetType = new PetType();
        clonePetType.setName(pet.getPetType().getName());
        clonePet.setPetType(clonePetType);
        Owner cloneOwner = new Owner();
        cloneOwner.setFirstName(pet.getOwner().getFirstName());
        cloneOwner.setLastName(pet.getOwner().getLastName());
        clonePet.setOwner(cloneOwner);
        return clonePet;
    }
}
