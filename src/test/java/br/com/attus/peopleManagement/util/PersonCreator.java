package br.com.attus.peopleManagement.util;

import br.com.attus.peopleManagement.entity.Address;
import br.com.attus.peopleManagement.entity.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonCreator {

    public static Person createPersonOne() {
        Person person = new Person();
        person.setName("Fulano de Tal");
        person.setBirth(LocalDate.of(1998, 6, 10));

        List<Address> listAddress = new ArrayList<>();
        listAddress.add(AddressCreator.createMainAddress());
        listAddress.add(AddressCreator.createAddress());

        person.setAddress(listAddress);

        return person;
    }

    public static Person createPersonTwo() {
        Person person = new Person();
        person.setName("Ciclano de Tal");
        person.setBirth(LocalDate.of(2000, 5, 15));

        List<Address> listAddress = new ArrayList<>();
        listAddress.add(AddressCreator.createMainAddress());
        listAddress.add(AddressCreator.createAddress());

        person.setAddress(listAddress);

        return person;
    }

}
