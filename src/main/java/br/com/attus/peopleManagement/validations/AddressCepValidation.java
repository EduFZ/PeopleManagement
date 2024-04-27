package br.com.attus.peopleManagement.validations;

import org.springframework.stereotype.Repository;

@Repository
public class AddressCepValidation {

    public boolean addressCepValidation(Long cep) {
        return cep == 8;
    }

}
