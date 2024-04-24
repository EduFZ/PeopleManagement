package br.com.attus.peopleManagement.service;

import br.com.attus.peopleManagement.entity.Person;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import br.com.attus.peopleManagement.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public List<Person> findAllPerson() {
        return personRepository.findAllPerson();
    }

    public Person findPersonById(Long idPerson) {
        return personRepository.findPersonById(idPerson);
    }

    public Person findPersonByName(String name) {
        return personRepository.findPersonByName(name);
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Person replacePerson(Long idPerson, Person person) throws ExceptionMessage {
        Person personById = personRepository.findPersonById(idPerson);

        if (personById == null){
            throw new ExceptionMessage("Pessoa não encontrada!");
        }

        BeanUtils.copyProperties(person, personById, "id");

//        if (person.getName() != null) {
//            personById.setName(person.getName());
//        }
//        if (person.getBirth() != null) {
//            personById.setBirth(person.getBirth());
//        }
//        if (person.getAddress() != null) {
//            personById.setAddress(person.getAddress());
//        }

        return personRepository.save(personById);

    }

    public void deletePerson(Long idPerson) {
        personRepository.deleteById(idPerson);
    }

}