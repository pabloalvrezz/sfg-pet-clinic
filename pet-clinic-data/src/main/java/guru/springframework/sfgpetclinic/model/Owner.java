package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "Owners")
public class Owner extends Person {

	@Column(name = "addres")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "telephone")
	private String telephone;

	// si se elimina un owner se elimina las mascotas que tiene este
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	private Set<Pet> pets = new HashSet<>();

	@Builder
	public Owner(Long id, String firstName, String lastName, String address, String city, String telephone,
			Set<Pet> pets) {
		super(id, firstName, lastName);
		this.address = address;
		this.city = city;
		this.telephone = telephone;

		if (pets != null) {
			this.pets = pets;
		}
	}

	public Pet getPet(String name) {
		return getPet2(name, false);
	}

	public Pet getPet2(String name, boolean ignoreNew) {
		name = name.toLowerCase();
		for (Pet pet : pets) {
			if (!ignoreNew || !pet.isNew()) {
				String compName = pet.getName();
				compName = compName.toLowerCase();
				if (compName.equals(name)) {
					return pet;
				}
			}
		}
		return null;
	}
}
