package com.cuongnghiem.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by cuongnghiem on 24/09/2021
 **/
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity{

    @Builder
    public Specialty(Long id, String description) {
        super(id);
        this.description = description;
    }

    @Column(name = "description")
    private String description;
}
