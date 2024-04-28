package br.com.attus.peopleManagement.service;

import br.com.attus.peopleManagement.entity.Address;
import br.com.attus.peopleManagement.entity.Person;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import br.com.attus.peopleManagement.repository.AddressRepository;
import br.com.attus.peopleManagement.repository.PersonRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressService addressService;


    public List<Person> findAllPerson() {
        return personRepository.findAllPerson();
    }

    public Person findPersonById(Long idPerson) {
        return personRepository.findPersonById(idPerson);
    }

    public Person findPersonByName(String name) {
        return personRepository.findPersonByName(name);
    }

    public Person savePerson(Person person) throws ExceptionMessage {
        Person savedPerson = personRepository.save(person);

        List<Address> listAddress = person.getAddress();

        if (listAddress != null && !listAddress.isEmpty()) {
            for (Address address : listAddress) {
                Address existingAddress = addressRepository.findAddressById(address.getIdAddress());

                if (existingAddress == null) {
                    address.setPerson(person);
                    addressService.saveAddress(address);
                } else {
                    address.setIdAddress(existingAddress.getIdAddress());
                    address.setPerson(person);
                }
            }
        } else {
            for (Address address : listAddress) {
                addressService.saveAddress(address);
            }
        }

        return savedPerson;
    }

    public Person replacePerson(Long idPerson, Person person) throws ExceptionMessage {
        Person personById = personRepository.findPersonById(idPerson);

        if (personById == null){
            throw new ExceptionMessage("Pessoa não encontrada!");
        }

        BeanUtils.copyProperties(person, personById, "id");

        return personRepository.save(personById);

    }

    public void deletePerson(Long idPerson) {
        personRepository.deleteById(idPerson);
    }

}
