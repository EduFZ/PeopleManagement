package br.com.attus.peopleManagement.controller;

import br.com.attus.peopleManagement.entity.Person;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import br.com.attus.peopleManagement.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;


    @GetMapping("/all")
    public ResponseEntity<List<Person>> findAllPerson() {
        return ResponseEntity.ok(personService.findAllPerson());
    }

    @GetMapping("/{idPerson}")
    public ResponseEntity<Person> findPersonById(@PathVariable Long idPerson) {
        return ResponseEntity.ok(personService.findPersonById(idPerson));
    }

    @GetMapping("/{namePerson}")
    public ResponseEntity<Person> findPersonByName(@PathVariable String namePerson) {
        return ResponseEntity.ok(personService.findPersonByName(namePerson));
    }

    @PostMapping("/savePerson")
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.CREATED);
    }

    @PostMapping("/replacePerson/{idPerson}")
    public ResponseEntity<Person> replacePerson(@PathVariable Long idPerson, @RequestBody Person person) throws ExceptionMessage {
        return new ResponseEntity<>(personService.replacePerson(idPerson, person), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idPerson}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long idPerson) {
        personService.deletePerson(idPerson);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
