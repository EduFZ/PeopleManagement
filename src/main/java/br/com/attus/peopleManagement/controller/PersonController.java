package br.com.attus.peopleManagement.controller;

import br.com.attus.peopleManagement.documentation.PersonDocumentation;
import br.com.attus.peopleManagement.entity.Person;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import br.com.attus.peopleManagement.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Person", description = "implementação dos métodos com retorno das requisições")
@RestController
@RequestMapping("person")
public class PersonController implements PersonDocumentation {
    @Autowired
    private PersonService personService;


    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Person>> findAllPerson() {
        return ResponseEntity.ok(personService.findAllPerson());
    }

    @Override
    @GetMapping("/{idPerson}")
    public ResponseEntity<Person> findPersonById(@PathVariable Long idPerson) {
        return ResponseEntity.ok(personService.findPersonById(idPerson));
    }

    @Override
    @GetMapping("/findByName/{namePerson}")
    public ResponseEntity<Person> findPersonByName(@PathVariable String namePerson) {
        return ResponseEntity.ok(personService.findPersonByName(namePerson));
    }

    @Override
    @PostMapping("/savePerson")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/replacePerson/{idPerson}")
    public ResponseEntity<Person> replacePerson(@PathVariable Long idPerson, @RequestBody Person person) throws ExceptionMessage{
        return new ResponseEntity<>(personService.replacePerson(idPerson, person), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/delete/{idPerson}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long idPerson) {
        personService.deletePerson(idPerson);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
