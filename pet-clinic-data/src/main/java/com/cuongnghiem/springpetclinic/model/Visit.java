package com.cuongnghiem.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by cuongnghiem on 24/09/2021
 **/
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity{

    @Builder
    public Visit(Long id, LocalDate date, String description, Pet pet) {
        super(id);
        this.date = date;
        this.description = description;
        this.pet = pet;
    }

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
