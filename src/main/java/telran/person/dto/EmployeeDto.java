package telran.person.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import telran.person.model.Address;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDto extends PersonDto{
	int salary;
	String company;
	public EmployeeDto(int id, String name, String birthDate, Address address, int salary, String company) {
		super(id, name, birthDate, address);
		this.salary = salary;
		this.company = company;
	}
	
}
