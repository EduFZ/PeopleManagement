package br.com.attus.peopleManagement.service;

import br.com.attus.peopleManagement.entity.Address;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import br.com.attus.peopleManagement.repository.AddressRepository;
import br.com.attus.peopleManagement.validations.AddressCepValidation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressCepValidation addressCepValidation;


    public List<Address> findAllAddress() {
        return addressRepository.findAllAddress();
    }

    public Address findAddressById(Long idAddress) {
        return addressRepository.findAddressById(idAddress);
    }

    public List<Address> findAllAddressByNamePerson(String name) {
        return addressRepository.findAllAddressByNamePerson(name);
    }

    public Address findMainAddressByNamePerson(String name) {
        return addressRepository.findMainAddressByNamePerson(name);
    }

    public Address saveAddress(Address address) throws ExceptionMessage {
        if (!addressCepValidation.addressCepValidation(address.getCep())) {
            throw new ExceptionMessage("CEP inválido");
        }
        return addressRepository.save(address);
    }

    //Neste método, já está incluso a opção de selecionar como endereço principal ou não.
    public Address replaceAddress(Long idAddress, Address address) throws ExceptionMessage {
        Address addressById = addressRepository.findAddressById(idAddress);

        if (addressById == null){
            throw new ExceptionMessage("Endereço não encontrado!");
        }

        BeanUtils.copyProperties(address, addressById, "id");

        return addressRepository.save(addressById);

    }

    public void deleteAddress(Long idAddress) {
        addressRepository.deleteById(idAddress);
    }

}
