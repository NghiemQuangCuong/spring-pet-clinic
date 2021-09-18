package com.cuongnghiem.springpetclinic.bootstrap;

import com.cuongnghiem.springpetclinic.model.Owner;
import com.cuongnghiem.springpetclinic.model.Vet;
import com.cuongnghiem.springpetclinic.services.OwnerService;
import com.cuongnghiem.springpetclinic.services.VetService;
import com.cuongnghiem.springpetclinic.services.map.OwnerServiceMap;
import com.cuongnghiem.springpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by cuongnghiem on 18/09/2021
 **/

@Component
public class DataLoad implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoad() {
        ownerService = new OwnerServiceMap();
        vetService = new VetServiceMap();
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Cuong");
        owner1.setLastName("Nghiem");
        ownerService.save(owner1.getId(), owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Quang");
        owner2.setLastName("Nghiem");
        ownerService.save(owner2.getId(), owner2);

        System.out.println("Owner Loaded...");

        Vet vet1 = new Vet();
        vet1.setId(3L);
        vet1.setFirstName("Anh");
        vet1.setLastName("Tran");
        vetService.save(vet1.getId(), vet1);

        System.out.println("Vet Loaded...");
    }
}
