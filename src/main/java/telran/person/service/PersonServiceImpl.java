package telran.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.person.dao.PersonRepository;
import telran.person.dto.ChildDto;
import telran.person.dto.EmployeeDto;
import telran.person.dto.PersonDto;
import telran.person.model.Child;
import telran.person.model.Employee;
import telran.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonRepository personRepository;

	@Override
	@Transactional // для методов которые используют транзакционность
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		Person person = convertPersonDtoToPerson(personDto);
		personRepository.save(person);
		return true;

	}

	private Person convertPersonDtoToPerson(PersonDto personDto) {
		if (personDto instanceof ChildDto) {
			ChildDto childDto = (ChildDto) personDto;
			return new Child(childDto.getId(), childDto.getName(), LocalDate.parse(childDto.getBirthDate()),
					childDto.getAddress(), childDto.getKindergarden());
		}
		if (personDto instanceof EmployeeDto) {
			EmployeeDto employeeDto = (EmployeeDto) personDto;
			return new Employee(employeeDto.getId(), employeeDto.getName(), LocalDate.parse(employeeDto.getBirthDate()),
					employeeDto.getAddress(), employeeDto.getSalary(), employeeDto.getCompany());
		}
		return Person.builder().id(personDto.getId()).name(personDto.getName())
				.birthDate(LocalDate.parse(personDto.getBirthDate())).address(personDto.getAddress()).build();
	}

	@Override
	public PersonDto findPersonById(int id) {
		Person person = personRepository.findById(id).orElse(null);
		if (person == null) {
			return null;
		}
		PersonDto personDto = convertorPersonToPersonDto(person);
		return personDto;

	}

	private PersonDto convertorPersonToPersonDto(Person person) {
		if (person instanceof Child) {
			Child child = (Child) person;
			return new ChildDto(child.getId(), child.getName(), child.getBirthDate().toString(), child.getAddress(),
					child.getKindergarden());
		}
		if (person instanceof Employee) {
			Employee employee = (Employee) person;
			return new EmployeeDto(employee.getId(), employee.getName(), employee.getBirthDate().toString(),
					employee.getAddress(), employee.getSalary(), employee.getCompany());
		}
		return PersonDto.builder().id(person.getId()).name(person.getName()).birthDate(person.getBirthDate().toString())
				.address(person.getAddress()).build();
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PersonDto> findPersonByName(String name) {

		return personRepository.findByName(name).stream().map(this::convertorPersonToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findPersonByAges(int min, int max) {
		LocalDate dateMin = LocalDate.now().minusYears(min + 1);
		LocalDate dateMax = LocalDate.now().minusYears(max + 1);

		return personRepository.findByBirthDateBetween(dateMax, dateMin).stream().map(this::convertorPersonToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findPersonsByCity(String city) {

		return personRepository.findByAddressCity(city).stream().map(this::convertorPersonToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findEmployeesBySalary(int min, int max) {
		
		return personRepository.findEmployeesBySalaryBetween(min, max).stream().map(this::convertorPersonToPersonDto)
				.collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findChildren() {
		return personRepository.findBy().stream().map(this::convertorPersonToPersonDto)
		.collect(Collectors.toList());
	
	}

}
