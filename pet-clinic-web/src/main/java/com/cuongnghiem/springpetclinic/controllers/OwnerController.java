package com.cuongnghiem.springpetclinic.controllers;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

/**
 * Created by cuongnghiem on 11/09/2021
 **/
@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedField(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }


    @RequestMapping({"/", ""})
    public String list(Model model, @ModelAttribute Owner owner, BindingResult result) {

        if (owner.getLastName() == null) {
            model.addAttribute("owners", ownerService.findAll());
            return "/owners/index";
        }

        Set<Owner> owners = ownerService.findAllByLastName(owner.getLastName());

        if (owners == null || owners.size() == 0)
        {
            result.rejectValue("lastName", "Not Found", "Not Found");
            return "/owners/find";
        } else if (owners.size() == 1) {
            Owner foundOwner = owners.iterator().next();
            return "redirect:/owners/" + foundOwner.getId();
        } else {
            model.addAttribute("owners", owners);
            return "/owners/index";
        }
    }

    @GetMapping({"/find"})
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "/owners/find";
    }

    @GetMapping("/{ownerId}")
    public String showOwner(@PathVariable String ownerId, Model model) {
        try {
            Owner owner = ownerService.findById(Long.valueOf(ownerId));
            if (owner == null)
                return "notimplementedyet";

            model.addAttribute("owner", owner);
            return "/owners/details";
        } catch (NumberFormatException exception) {
            return "notimplementedyet";
        }
    }

    @GetMapping("/new")
    public String getNewOwnerView(Model model) {
        model.addAttribute("owner", new Owner());
        return "/owners/newOrUpdateOwner";
    }

    @PostMapping("/new")
    public String addNewOwner(@Valid @ModelAttribute Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "/owners/newOrUpdateOwner";
        }

        ownerService.save(owner);
        return "redirect:/owners";
    }

    @GetMapping("/{ownerId}/edit")
    public String getOwnerEditView(@PathVariable Long ownerId, Model model) {
        Owner owner = ownerService.findById(ownerId);
        if (owner == null)
            return "notimplementedyet";

        model.addAttribute("owner", owner);
        return "/owners/newOrUpdateOwner";
    }

    @PostMapping("/{ownerId}/edit")
    public String editOwner(@PathVariable Long ownerId, @Valid @ModelAttribute Owner owner, BindingResult result) {
        owner.setId(ownerId);

        if (result.hasErrors())
            return "/owners/newOrUpdateOwner";

        ownerService.save(owner);
        return "redirect:/owners";
    }
}
