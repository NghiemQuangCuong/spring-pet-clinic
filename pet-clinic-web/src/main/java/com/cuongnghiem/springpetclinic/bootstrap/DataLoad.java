package com.cuongnghiem.springpetclinic.bootstrap;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.model.PetType;
import com.cuongnghiem.springpetclinic.model.Vet;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import com.cuongnghiem.springpetclinic.services.PetTypeService;
import com.cuongnghiem.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        Owner owner1 = new Owner();
        owner1.setFirstName("Cuong");
        owner1.setLastName("Nghiem");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Quang");
        owner2.setLastName("Nghiem");
        ownerService.save(owner2);

        System.out.println("Owner Loaded...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Anh");
        vet1.setLastName("Tran");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Anh");
        vet2.setLastName("Nghiem");
        vetService.save(vet2);

        System.out.println("Vet Loaded...");

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
    }
}
