package br.com.attus.peopleManagement.util;

import br.com.attus.peopleManagement.entity.Address;

public class AddressCreator {

    public static Address createMainAddress() {

        Address address = new Address();
        address.setIdAddress(1L);
        address.setStreet("Rua Dois de Janeiro");
        address.setCep(23456000L);
        address.setNumber(15L);
        address.setCity("Campinas");
        address.setState("São Paulo");
        address.setMainAddress(true);

        return address;
    }

    public static Address createAddress() {

        Address address = new Address();
        address.setIdAddress(2L);
        address.setStreet("Rua Dois de Janeiro");
        address.setCep(12345000L);
        address.setNumber(12L);
        address.setCity("Joinville");
        address.setState("Santa Catarina");
        address.setMainAddress(false);

        return address;
    }

}
