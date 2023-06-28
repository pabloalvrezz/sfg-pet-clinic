package guru.springframework.sfgpetclinic.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Visit")
public class Visit extends BaseEntity {

	@Column(name = "Date")
	private LocalDate date;
	
	@Column(name = "Description")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;

	
}
