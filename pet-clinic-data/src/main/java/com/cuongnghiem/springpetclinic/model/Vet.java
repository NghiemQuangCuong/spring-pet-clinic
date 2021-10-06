package com.cuongnghiem.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "vet")
public class Vet extends Person {

    @Builder
    public Vet(Long id, String firstName, String lastName, Set<Specialty> specialties) {
        super(id, firstName, lastName);
        this.specialties = (specialties == null) ? new HashSet<>() : specialties;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vets_specialties",
                joinColumns = @JoinColumn(name = "vet_id"),
                inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties = new HashSet<>();
}
