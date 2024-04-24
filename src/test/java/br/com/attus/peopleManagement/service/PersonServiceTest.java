package br.com.attus.peopleManagement.service;

import br.com.attus.peopleManagement.entity.Person;
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

}