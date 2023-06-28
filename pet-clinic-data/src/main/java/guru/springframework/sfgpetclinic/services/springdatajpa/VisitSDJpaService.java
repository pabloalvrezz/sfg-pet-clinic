package guru.springframework.sfgpetclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import guru.springframework.sfgpetclinic.services.VisitService;

public class VisitSDJpaService implements VisitService {

	private final VisitRepository visitRepository;

	public VisitSDJpaService(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public Set<Visit> findAll() {
		Set<Visit> visits = new HashSet<>();

		visitRepository.findAll().forEach(visits::add);

		return null;
	}

	@Override
	public Visit findById(Long id) {
		Optional<Visit> optionalVisit = visitRepository.findById(id);

		if (optionalVisit.isPresent())
			return optionalVisit.get();

		return null;
	}

	@Override
	public Visit save(Visit object) {
		return visitRepository.save(object);
	}

	@Override
	public void delete(Visit object) {
		visitRepository.delete(object);

	}

	@Override
	public void deleteById(Long id) {
		visitRepository.deleteById(id);

	}

}
