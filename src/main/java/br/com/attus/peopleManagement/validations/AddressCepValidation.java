package br.com.attus.peopleManagement.validations;

public class AddressCepValidation {

    public boolean addressCepValidation(Long cep) {
        return cep == 8;
    }

}
