package telran.person.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends Person {

	private static final long serialVersionUID = 1L;

	int salary;
	String company;

	public Employee(int id, String name, LocalDate birthDate, Address address, int salary, String company) {
		super(id, name, birthDate, address);
		this.salary = salary;
		this.company = company;
	}

}
