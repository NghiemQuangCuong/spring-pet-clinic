package com.cuongnghiem.springpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cuongnghiem on 11/09/2021
 **/
@RequestMapping("/owner")
@Controller
public class OwnerController {

    @RequestMapping({"/", "", "/index", "/index.html"})
    public String list() {
        return "owners/index";
    }
}