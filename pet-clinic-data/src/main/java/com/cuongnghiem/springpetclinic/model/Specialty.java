package com.cuongnghiem.springpetclinic.model;

/**
 * Created by cuongnghiem on 24/09/2021
 **/

public class Specialty extends BaseEntity{

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
