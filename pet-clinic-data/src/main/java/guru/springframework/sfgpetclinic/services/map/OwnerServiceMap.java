package guru.springframework.sfgpetclinic.services.map;

import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

	private final PetTypeService petTypeService;
	private final PetService petService;

	public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
		this.petTypeService = petTypeService;
		this.petService = petService;
	}

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}

	@Override
	public Owner findById(Long id) {

		return super.findById(id);
	}

	// metodo que usaremos para guardar el owner
	@Override
	public Owner save(Owner object) {

		// si intentamos guardar un objeto que no sea nulo se guardara,
		// sino guardaremos un objeto nulo
		if (object != null) {
			// si el objeto tiene mascotas, realizaremos una operacion con cada una de estas
			if (object.getPets() != null) {
				// para cada mascota comprobaremos que el tipo no sea nulo
				object.getPets().forEach(pet -> {
					if (pet.getPetType() != null) {
						// si el id de la mascota es nulo quiere decir que todavia no esta guardado, asi
						// que lo guardamos
						if (pet.getPetType().getId() == null) {
							pet.setPetType(petTypeService.save(pet.getPetType()));
						}

					}
					// si el tipo de la mascota es nullo decimos que se requiere el tipo de esta
					else {
						throw new RuntimeException("Pet Type is required");
					}
					
					// si la mascota tiene un id nulo guardaremos esta y le agregaremos un id
					if(pet.getId() == null) {
						Pet savedPet = petService.save(pet);
						pet.setId(savedPet.getId());
					}
				});
			}
			return super.save(object);
		}

		return null;
	}

	@Override
	public void delete(Owner object) {
		super.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		super.deleteById(id);
	}

	@Override
	public Owner findByLastName(String lastName) {
		return null;
	}

}
