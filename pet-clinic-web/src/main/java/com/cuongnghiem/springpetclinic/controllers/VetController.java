package com.cuongnghiem.springpetclinic.controllers;

import com.cuongnghiem.springpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cuongnghiem on 11/09/2021
 **/
@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets", "/vets.html"})
    public String list(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }
}
