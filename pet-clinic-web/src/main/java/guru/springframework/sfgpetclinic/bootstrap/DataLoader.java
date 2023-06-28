package guru.springframework.sfgpetclinic.bootstrap;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    
	public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
		this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
	}

	@Override
	public void run(String... args) throws Exception {

		PetType dog = new PetType();
		dog.setName("Dog");
		PetType savedDogPetType = petTypeService.save(dog);

		PetType cat = new PetType();
		cat.setName("Cat");
		PetType savedCatPetType = petTypeService.save(cat);

		System.out.println("Tipos de mascota cargados......");
		
		Owner owner1 = new Owner();
		owner1.setFirstName("Roberto");
		owner1.setLastName("Collacio");
		owner1.setAddress("Avenida Galicia 34");
		owner1.setCity("Gijón");
		owner1.setTelephone("123456789");
		
		Pet robertosPet = new Pet();
		robertosPet.setPetType(savedDogPetType);
		robertosPet.setOwner(owner1);
		robertosPet.setBirthDate(LocalDate.now());
		robertosPet.setName("Chipas");
		owner1.getPets().add(robertosPet);
		
		ownerService.save(owner1);

		Owner owner2 = new Owner();
		owner2.setFirstName("Carlos");
		owner2.setLastName("Pérez");
		owner2.setAddress("Calle brasil 2");
		owner2.setCity("Gijón");
		owner2.setTelephone("999453275");
		ownerService.save(owner2);

		Pet carlosPet = new Pet();
		carlosPet.setPetType(savedCatPetType);
		carlosPet.setOwner(owner2);
		carlosPet.setBirthDate(LocalDate.now());
		carlosPet.setName("Mickey");
		owner2.getPets().add(carlosPet);
		
		System.out.println("Owners cargados.......");

		Vet vet1 = new Vet();
		vet1.setFirstName("Pepe");
		vet1.setLastName("Suco");

		vetService.save(vet1);

		Vet vet2 = new Vet();
		vet2.setFirstName("Ramón");
		vet2.setLastName("Peréz");

		vetService.save(vet2);

		System.out.println("Vets cargados......");

	}

}
