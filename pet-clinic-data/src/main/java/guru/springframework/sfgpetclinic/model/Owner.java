package guru.springframework.sfgpetclinic.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
	
}
