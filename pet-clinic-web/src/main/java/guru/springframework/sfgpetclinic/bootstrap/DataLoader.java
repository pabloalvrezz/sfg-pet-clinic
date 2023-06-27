package guru.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner{

	private final OwnerService ownerService;
	private final VetService vetService;
	
	public DataLoader() {
		ownerService = new OwnerServiceMap();
		vetService = new VetServiceMap();
	}
			

	@Override
	public void run(String... args) throws Exception {
		
		Owner owner1 = new Owner();
		owner1.setId(1L);
		owner1.setFirstName("Roberto");
		owner1.setLastName("Collacio");
		
		ownerService.save(owner1);
		

		Owner owner2 = new Owner();
		owner2.setId(2L);
		owner2.setFirstName("Carlos");
		owner2.setLastName("Pérez");
		
		ownerService.save(owner2);
		
		System.out.println("Owners cargados.......");
		
		Vet vet1= new Vet();
		vet1.setId(1L);
		vet1.setFirstName("Pepe");
		vet1.setLastName("Suco");
		
		vetService.save(vet1);
		
		Vet vet2 = new Vet();
		vet2.setId(2L);
		vet2.setFirstName("Ramón");
		vet2.setLastName("Peréz");
		
		vetService.save(vet2);
		
		System.out.println("Vets cargados......");
		
		
	}
	
	
}
