package br.com.attus.peopleManagement.controller;

import br.com.attus.peopleManagement.entity.Person;
import br.com.attus.peopleManagement.service.PersonService;
import br.com.attus.peopleManagement.util.PersonCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;
    @Autowired
    private JacksonTester<Person> jsonPerson;


    @Test
    void shouldReturnStatusOkWithFindAllPerson() throws Exception {
        List<Person> person = new ArrayList<>();
        person.add(PersonCreator.createPersonOne());
        person.add(PersonCreator.createPersonTwo());

        BDDMockito.given(personService.findAllPerson()).willReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.get("/person/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnStatusOkWithFindPersonById() throws Exception {
        Person person = PersonCreator.createPersonOne();
        Long idPerson = 1L;

        BDDMockito.given(personService.findPersonById(BDDMockito.anyLong())).willReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.get("/person/{idPerson}", idPerson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnStatusOkWithFindPersonByName() throws Exception {
        Person person = PersonCreator.createPersonOne();
        String namePerson = "Fulano";

        BDDMockito.given(personService.findPersonByName("Fulano")).willReturn(person);

        mockMvc.perform(MockMvcRequestBuilders.get("/person/findByName/{namePerson}", namePerson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnStatusCreatedWithSavePerson() throws Exception {
        Person person = PersonCreator.createPersonOne();

        BDDMockito.given(personService.savePerson(person)).willReturn(person);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/person/savePerson")
                        .content(jsonPerson.write(person).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());

    }

    @Test
    void shouldReturnStatusCreatedWithReplacePerson() throws Exception {
        Person person = PersonCreator.createPersonOne();
        Long idPerson = 1L;

        BDDMockito.given(personService.replacePerson(idPerson, person)).willReturn(person);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/person/replacePerson/{idPerson}", idPerson)
                        .content(jsonPerson.write(person).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());

    }

    @Test
    void shoulReturnNoContentWithDeletePerson() throws Exception {
        Long idPerson = 1L;

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/person/delete/{idPerson}", idPerson))
                .andReturn().getResponse();

        Assertions.assertEquals(204, response.getStatus());

    }

}