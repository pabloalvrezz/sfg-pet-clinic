package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.services.PetService;

@Service
@Profile("spingdatajpa")
public class PetSDJpaService implements PetService {

	private PetRepository petRepository;

	public PetSDJpaService(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public Set<Pet> findAll() {
		Set<Pet> pets = new HashSet<>();
		
		petRepository.findAll().forEach(pets::add);
		
		return null;
	}

	@Override
	public Pet findById(Long id) {
		Optional<Pet> optionalPet = petRepository.findById(id);
		
		if(optionalPet.isPresent())
			return optionalPet.get();
		
		return null;
	}

	@Override
	public Pet save(Pet object) {
		return petRepository.save(object);
	}

	@Override
	public void delete(Pet object) {
		petRepository.delete(object);

	}

	@Override
	public void deleteById(Long id) {
		petRepository.deleteById(id);

	}

}
