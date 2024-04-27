package br.com.attus.peopleManagement.service;

import br.com.attus.peopleManagement.entity.Address;
import br.com.attus.peopleManagement.entity.Person;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import br.com.attus.peopleManagement.repository.AddressRepository;
import br.com.attus.peopleManagement.repository.PersonRepository;
import br.com.attus.peopleManagement.util.AddressCreator;
import br.com.attus.peopleManagement.util.PersonCreator;
import br.com.attus.peopleManagement.validations.AddressCepValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @InjectMocks
    private AddressService addressService;

    @Mock
    private AddressRepository addressRepository;
    @Mock
    private AddressCepValidation addressCepValidation;

    @Test
    void shouldReturnListAddressWithFindAllAddress() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(AddressCreator.createMainAddress());
        addresses.add(AddressCreator.createAddress());

        BDDMockito.given(addressRepository.findAllAddress()).willReturn(addresses);

        List<Address> allAddress = addressService.findAllAddress();

        Assertions.assertNotNull(allAddress);
        Assertions.assertEquals(2, allAddress.size());

    }

    @Test
    void shouldReturnAddressWithFindAddressById() {
        Address address = AddressCreator.createMainAddress();

        BDDMockito.given(addressRepository.findAddressById(BDDMockito.anyLong())).willReturn(address);

        Address findAddress = addressService.findAddressById(1L);

        Assertions.assertEquals(1L, findAddress.getIdAddress());
        Assertions.assertEquals(address.getStreet(), findAddress.getStreet());

    }

    @Test
    void shouldReturnListAddressWithFindAddressByNamePerson() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(AddressCreator.createMainAddress());
        addresses.add(AddressCreator.createAddress());

        BDDMockito.given(addressRepository.findAllAddressByNamePerson(BDDMockito.anyString())).willReturn(addresses);

        List<Address> findAddress = addressService.findAllAddressByNamePerson("Fulano");

        Assertions.assertEquals(1L, findAddress.get(0).getIdAddress());
        Assertions.assertNotNull(findAddress);

    }

    @Test
    void shouldReturnMainAddressWithFindAddressByNamePerson() {
        Address address = AddressCreator.createMainAddress();

        BDDMockito.given(addressRepository.findMainAddressByNamePerson(BDDMockito.anyString())).willReturn(address);

        Address findAddress = addressService.findMainAddressByNamePerson("Fulano");

        Assertions.assertEquals(1L, findAddress.getIdAddress());
        Assertions.assertEquals(address.getStreet(), findAddress.getStreet());

    }

    @Test
    void shouldReturnAddressWithSaveAddress() throws ExceptionMessage {
        Address address = AddressCreator.createMainAddress();

        addressService.saveAddress(address);

        BDDMockito.then(addressRepository).should().save(address);
        Assertions.assertEquals(1L, address.getIdAddress());

    }

    @Test
    void shouldReturnExceptionMessageWithSaveAddress() throws ExceptionMessage {
        Address address = AddressCreator.createMainAddress();
        address.setCep(12345L);

        BDDMockito.given(addressCepValidation.addressCepValidation(address.getCep())).willReturn(false);

        ExceptionMessage exceptionMessage = Assertions.assertThrows(ExceptionMessage.class, () -> addressService.saveAddress(address));
        Assertions.assertEquals("CEP inválido", exceptionMessage.getMessage());

    }

    @Test
    void shouldReturnAddressWithReplaceAddress() throws ExceptionMessage {
        Address address = AddressCreator.createMainAddress();
        Address addressTwo = AddressCreator.createAddress();

        BDDMockito.given(addressRepository.findAddressById(BDDMockito.anyLong())).willReturn(addressTwo);
        BDDMockito.given(addressRepository.save(BDDMockito.any(Address.class))).willReturn(addressTwo);

        Address addressNew = addressService.replaceAddress(1L, address);

        Assertions.assertEquals(addressTwo.getStreet(), addressNew.getStreet());
        BDDMockito.then(addressRepository).should().save(addressTwo);

    }

    @Test
    void shoulReturnExceptionMessageWithIdAddressNotFound() throws ExceptionMessage {
        Address address = AddressCreator.createMainAddress();

        BDDMockito.given(addressRepository.findAddressById(BDDMockito.anyLong())).willReturn(null);

        ExceptionMessage exceptionMessage = Assertions.assertThrows(ExceptionMessage.class, () -> addressService.replaceAddress(1L, address));
        Assertions.assertEquals("Endereço não encontrado!", exceptionMessage.getMessage());
    }

    @Test
    void shouldReturnEmptyWithDeleteAddress() {

        addressService.deleteAddress(1L);

        BDDMockito.then(addressRepository).should().deleteById(1L);

    }


}