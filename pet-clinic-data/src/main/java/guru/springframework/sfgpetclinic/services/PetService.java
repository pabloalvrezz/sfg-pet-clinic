package guru.springframework.sfgpetclinic.services;

import java.util.Set;

import guru.springframework.sfgpetclinic.model.Pet;

public interface PetService {
	Pet finById(Long id);

	Pet save(Pet pet);

	Set<Pet> findAll();
}
