package telran.person.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = { "id" })
@Builder
@Entity
@Table(name = "persons") // название таблицы в базе данных (изначально(если не заданно) Person->persons)
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	int id;
	String name;
	LocalDate birthDate;
	//@EmbeddedId для того чтобы сделать поле Id для базы данных
	Address address;

}
