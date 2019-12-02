package telran.person.service;

import java.time.LocalDate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import telran.person.dao.PersonRepository;
import telran.person.dto.PersonDto;
import telran.person.model.Person;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired
	PersonRepository personRepository;

	@Override
	@Transactional//для методов которые используют транзакционность
	public boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		Person person = convertPersonDtoToPerson(personDto);
		personRepository.save(person);
		return true;

	}

	private Person convertPersonDtoToPerson(PersonDto personDto) {
		// TODO Auto-generated method stub
		return Person.builder().name(personDto.getName()).id(personDto.getId())
				.birthDate(LocalDate.parse(personDto.getBirthDate())).build();
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

		return PersonDto.builder().id(person.getId()).name(person.getName()).birthDate(person.getBirthDate().toString())
				.build();
	}

	@Override
	public Iterable<PersonDto> findPersonByName(String name) {
		
		return personRepository.findByName(name).stream().map(this::convertorPersonToPersonDto).collect(Collectors.toList());
	}

	@Override
	public Iterable<PersonDto> findPersonByAges(int min, int max) {
		LocalDate dateMin= LocalDate.now().minusYears(min+1);
		LocalDate dateMax=LocalDate.now().minusYears(max+1);
		
		return personRepository.findPersonByAges(dateMax, dateMin)
				.stream().map(this::convertorPersonToPersonDto).collect(Collectors.toList());
	}

}
