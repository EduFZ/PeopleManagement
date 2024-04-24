package br.com.attus.peopleManagement.repository;

import br.com.attus.peopleManagement.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("SELECT p FROM Person p")
    List<Person> findAllPerson();

    @Query("SELECT p FROM Person p WHERE p.idPerson = :idPerson")
    Person findPersonById(@Param("idPerson") Long idPerson);

    @Query("SELECT p FROM Person p WHERE p.name = :name")
    Person findPersonByName(@Param("name") String name);

}
