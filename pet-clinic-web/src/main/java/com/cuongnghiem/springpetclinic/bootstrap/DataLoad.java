package com.cuongnghiem.springpetclinic.bootstrap;

import com.cuongnghiem.springpetclinic.model.*;
import com.cuongnghiem.springpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Created by cuongnghiem on 18/09/2021
 **/

@Component
public class DataLoad implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final VisitService visitService;
    private final SpecialtyService specialtyService;

    public DataLoad(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
                    VisitService visitService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.visitService = visitService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) {
        int count = vetService.findAll().size();
        if (count == 0)
            loadData();
    }

    private void loadData() {
        PetType cat = PetType.builder().name("Cat").build();
        petTypeService.save(cat);
        PetType dog = PetType.builder().name("Dog").build();
        petTypeService.save(dog);
        PetType bird = PetType.builder().name("Bird").build();
        petTypeService.save(bird);

        System.out.println("PetType Loaded...");

        Owner owner1 = Owner.builder()
                .firstName("Cuong")
                .lastName("Nghiem")
                .address("414 Doan Van Bo")
                .city("HCM City")
                .phoneNumber("0352893327")
                .build();
        Pet cuongsPet = Pet.builder()
                .petType(cat)
                .name("Meow")
                .birthDay(LocalDate.of(2015, 11, 7))
                .owner(owner1)
                .build();
        owner1.getPets().add(cuongsPet);
        ownerService.save(owner1);

        Visit visit = Visit.builder()
                .description("Sneezing Cat")
                .date(LocalDate.now())
                .pet(cuongsPet)
                .build();
        visitService.save(visit);

        Owner owner2 = Owner.builder()
                .firstName("Quang")
                .lastName("Nghiem")
                .address("30/4 Street")
                .city("Phu Quoc")
                .phoneNumber("0913138171")
                .build();
        Pet quangsPet = Pet.builder()
                .petType(dog)
                .name("Milu")
                .birthDay(LocalDate.of(2017, 4, 11))
                .owner(owner2)
                .build();
        owner2.getPets().add(quangsPet);
        ownerService.save(owner2);

        System.out.println("Owner with Pet Loaded...");

        Vet vet1 = Vet.builder()
                .firstName("Anh")
                .lastName("Tran")
                .build();
        Specialty radiology = Specialty.builder().description("Radiology").build();
        specialtyService.save(radiology);
        vet1.getSpecialties().add(radiology);
        vetService.save(vet1);

        Vet vet2 = Vet.builder()
                .firstName("Anh")
                .lastName("Nghiem")
                .build();
        Specialty surgery = Specialty.builder().description("Surgery").build();
        Specialty dentistry = Specialty.builder().description("Dentistry").build();
        specialtyService.save(surgery);
        specialtyService.save(dentistry);
        vet2.getSpecialties().add(radiology);
        vet2.getSpecialties().add(surgery);
        vet2.getSpecialties().add(dentistry);
        vetService.save(vet2);

        System.out.println("Vet Loaded...");
    }
}
