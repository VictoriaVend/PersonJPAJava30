package telran.person.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import telran.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {
	List<Person> findByName(String name);

	@Query("select p from Person p where p.birthDate > ?1 and p.birthDate < ?2")
	List<Person> findPersonByAges(LocalDate dataMax, LocalDate dataMin);

}
