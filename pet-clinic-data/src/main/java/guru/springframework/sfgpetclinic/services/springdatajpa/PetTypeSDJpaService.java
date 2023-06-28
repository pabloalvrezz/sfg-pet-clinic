package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.repositories.PetTyperRepository;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Service
@Profile("spingdatajpa")
public class PetTypeSDJpaService implements PetTypeService {

	private final PetTyperRepository petTypeRepository;

	public PetTypeSDJpaService(PetTyperRepository petTypeRepository) {
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Set<PetType> findAll() {
		Set<PetType> types = new HashSet<>();
		
		petTypeRepository.findAll().forEach(types::add);

		return null;
	}

	@Override
	public PetType findById(Long id) {
		Optional <PetType> optionalPetType = petTypeRepository.findById(id);
		
		if(optionalPetType.isPresent())
			return optionalPetType.get();
		
		return null;
	}

	@Override
	public PetType save(PetType object) {
		
		return petTypeRepository.save(object);
	}

	@Override
	public void delete(PetType object) {
		petTypeRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		petTypeRepository.deleteById(id);

	}

}
