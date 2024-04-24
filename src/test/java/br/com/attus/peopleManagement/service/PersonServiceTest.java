package br.com.attus.peopleManagement.service;

import br.com.attus.peopleManagement.entity.Person;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import br.com.attus.peopleManagement.repository.PersonRepository;
import br.com.attus.peopleManagement.util.PersonCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    void shouldReturnListPersonWithFindAllPerson() {
        List<Person> person = new ArrayList<>();
        person.add(PersonCreator.createPersonOne());
        person.add(PersonCreator.createPersonTwo());

        BDDMockito.given(personRepository.findAllPerson()).willReturn(person);

        List<Person> allPerson = personService.findAllPerson();

        Assertions.assertNotNull(allPerson);
        Assertions.assertEquals(2, allPerson.size());

    }

    @Test
    void shouldReturnPersonWithFindPersonById() {
        Person person = PersonCreator.createPersonOne();

        BDDMockito.given(personRepository.findPersonById(BDDMockito.anyLong())).willReturn(person);

        Person findPerson = personService.findPersonById(1L);

        Assertions.assertEquals(1L, findPerson.getIdPerson());
        Assertions.assertEquals(person.getName(), findPerson.getName());

    }

    @Test
    void shouldReturnPersonWithFindPersonByName() {
        Person person = PersonCreator.createPersonOne();

        BDDMockito.given(personRepository.findPersonByName(BDDMockito.anyString())).willReturn(person);

        Person findPerson = personService.findPersonByName("Fulano");

        Assertions.assertEquals(1L, findPerson.getIdPerson());
        Assertions.assertEquals(person.getName(), findPerson.getName());

    }

    @Test
    void shouldReturnPersonWithSavePerson() {
        Person person = PersonCreator.createPersonOne();

        personService.savePerson(person);

        BDDMockito.then(personRepository).should().save(person);
        Assertions.assertEquals(1L, person.getIdPerson());

    }

    @Test
    void shouldReturnPersonWithReplacePerson() throws ExceptionMessage {
        Person person = PersonCreator.createPersonOne();
        Person personTwo = PersonCreator.createPersonTwo();

        BDDMockito.given(personRepository.findPersonById(BDDMockito.anyLong())).willReturn(personTwo);
        BDDMockito.given(personRepository.save(BDDMockito.any(Person.class))).willReturn(personTwo);

        Person personNew = personService.replacePerson(1L, person);

        Assertions.assertEquals(personTwo.getName(), personNew.getName());
        BDDMockito.then(personRepository).should().save(personTwo);

    }

    @Test
    void shoulReturnExceptionMessageWithIdPersonNotFound() throws ExceptionMessage {
        Person person = PersonCreator.createPersonOne();

        BDDMockito.given(personRepository.findPersonById(BDDMockito.anyLong())).willReturn(null);

        ExceptionMessage exceptionMessage = Assertions.assertThrows(ExceptionMessage.class, () -> personService.replacePerson(1L, person));
        Assertions.assertEquals("Pessoa não encontrada!", exceptionMessage.getMessage());
    }

    @Test
    void shouldReturnEmptyWithDeletePerson() {

        personService.deletePerson(1L);

        BDDMockito.then(personRepository).should().deleteById(1L);

    }


}