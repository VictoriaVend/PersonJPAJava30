package telran.person.service;

import java.time.LocalDate;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<PersonDto> findPersonByAges(int min, int max) {
		// TODO Auto-generated method stub
		return null;
	}

}
