package com.cuongnghiem.springpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity{

    @Builder
    public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDay, Set<Visit> visits) {
        super(id);
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        this.birthDay = birthDay;
        this.visits = (visits == null) ? new HashSet<>() : visits;
    }

    @Column(name = "name")
    @NotBlank(message = "Pet's name must not be empty")
    private String name;

    @OneToOne
    @JoinColumn(name = "pet_type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "birth_day")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Birthdate must be past or present")
    private LocalDate birthDay;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();
}
