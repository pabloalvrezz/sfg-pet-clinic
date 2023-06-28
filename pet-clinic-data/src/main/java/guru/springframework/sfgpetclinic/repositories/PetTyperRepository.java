package guru.springframework.sfgpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.sfgpetclinic.model.PetType;

public interface PetTyperRepository extends CrudRepository<PetType, Long>{

}
