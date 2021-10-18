package com.cuongnghiem.springpetclinic.controllers;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping({"/", "", "/index", "/index.html"})
    public String list(Model model, @ModelAttribute Owner owner, BindingResult result) {

        if (owner.getLastName() == null) {
            model.addAttribute("owners", ownerService.findAll());
            return "/owners/index";
        }

        Set<Owner> owners = ownerService.findAllByLastName(owner.getLastName());

        if (owners == null || owners.size() == 0)
        {
            result.rejectValue("lastName", "Not Found", "notFound");
            return "/owners/find";
        } else if (owners.size() == 1) {
            Owner foundOwner = owners.iterator().next();
            return "redirect:/owners/" + foundOwner.getId();
        } else {
            model.addAttribute("owners", owners);
            return "/owners/index";
        }
    }

    @RequestMapping({"/find"})
    public String findOwners(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "/owners/find";
    }

    @GetMapping("/{ownerId}")
    public String showOwner(@PathVariable String ownerId, Model model) {
        try {
            Owner owner = ownerService.findById(Long.valueOf(ownerId));
            model.addAttribute("owner", owner);
            return "/owners/details";
        } catch (NumberFormatException exception) {
            return "notimplementedyet";
        }
    }
}
