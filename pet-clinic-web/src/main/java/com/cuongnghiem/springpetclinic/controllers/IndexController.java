package com.cuongnghiem.springpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by cuongnghiem on 10/09/2021
 **/
@Controller
public class IndexController {

    @RequestMapping({"", "/", "/index", "/index.html"})
    public String index(){
        return "index";
    }

    @RequestMapping({"/oups"})
    public String oupsHandler(){

        return "notimplementedyet";
    }
}
