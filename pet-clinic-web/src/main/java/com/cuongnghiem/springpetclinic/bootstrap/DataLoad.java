package com.cuongnghiem.springpetclinic.bootstrap;

import com.cuongnghiem.springpetclinic.model.*;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import com.cuongnghiem.springpetclinic.services.PetTypeService;
import com.cuongnghiem.springpetclinic.services.VetService;
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

    public DataLoad(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        PetType cat = new PetType();
        cat.setName("Cat");
        petTypeService.save(cat);
        PetType dog = new PetType();
        dog.setName("Dog");
        petTypeService.save(dog);
        PetType bird = new PetType();
        bird.setName("Bird");
        petTypeService.save(bird);

        System.out.println("PetType Loaded...");

        Owner owner1 = new Owner();
        owner1.setFirstName("Cuong");
        owner1.setLastName("Nghiem");
        owner1.setAddress("414 Doan Van Bo");
        owner1.setCity("HCM City");
        owner1.setPhoneNumber("0352893327");
        Pet cuongsPet = new Pet();
        cuongsPet.setPetType(cat);
        cuongsPet.setName("Meow");
        cuongsPet.setBirthDay(LocalDate.of(2015, 11, 7));
        cuongsPet.setOwner(owner1);
        owner1.getPets().add(cuongsPet);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Quang");
        owner2.setLastName("Nghiem");
        owner2.setAddress("30/4 Street");
        owner2.setCity("Phu Quoc");
        owner2.setPhoneNumber("0913138171");
        Pet quangsPet = new Pet();
        quangsPet.setPetType(dog);
        quangsPet.setName("Milu");
        quangsPet.setBirthDay(LocalDate.of(2017, 4, 11));
        quangsPet.setOwner(owner2);
        owner2.getPets().add(quangsPet);
        ownerService.save(owner2);

        System.out.println("Owner with Pet Loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Anh");
        vet1.setLastName("Tran");
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        vet1.getSpecialties().add(radiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Anh");
        vet2.setLastName("Nghiem");
        Specialty surgery = new Specialty();
        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        surgery.setDescription("Surgery");
        vet2.getSpecialties().add(radiology);
        vet2.getSpecialties().add(surgery);
        vet2.getSpecialties().add(dentistry);
        vetService.save(vet2);

        System.out.println("Vet Loaded...");
    }
}
