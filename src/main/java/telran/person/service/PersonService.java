package telran.person.service;

import telran.person.dto.CityPopulationDto;
import telran.person.dto.PersonDto;

public interface PersonService {
	boolean addPerson(PersonDto personDto);

	PersonDto findPersonById(int id);

	Iterable<PersonDto> findPersonByName(String name);

	Iterable<PersonDto> findPersonByAges(int min, int max);

	Iterable<PersonDto> findPersonsByCity(String city);

	Iterable<PersonDto> findEmployeesBySalary(int min, int max);

	Iterable<PersonDto> findChildren();
	
	Iterable<CityPopulationDto> getCityPopylation();
}
