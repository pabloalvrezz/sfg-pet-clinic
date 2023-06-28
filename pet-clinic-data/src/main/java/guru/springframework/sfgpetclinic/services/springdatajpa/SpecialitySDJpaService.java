package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialityRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;

public class SpecialitySDJpaService implements SpecialtyService {

	private final SpecialityRepository specialityRepository;

	public SpecialitySDJpaService(SpecialityRepository specialityRepository) {
		this.specialityRepository = specialityRepository;
	}

	@Override
	public Set<Speciality> findAll() {
		Set<Speciality> specialities = new HashSet<>();

		specialityRepository.findAll().forEach(specialities::add);

		return null;
	}

	@Override
	public Speciality findById(Long id) {
		Optional<Speciality> optionalSpeciality = specialityRepository.findById(id);

		if (optionalSpeciality.isPresent())
			return optionalSpeciality.get();

		return null;
	}

	@Override
	public Speciality save(Speciality object) {
		return specialityRepository.save(object);
	}

	@Override
	public void delete(Speciality object) {
		specialityRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		specialityRepository.deleteById(id);

	}

}
