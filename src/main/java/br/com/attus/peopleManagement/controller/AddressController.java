package br.com.attus.peopleManagement.controller;

import br.com.attus.peopleManagement.entity.Address;
import br.com.attus.peopleManagement.entity.Person;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import br.com.attus.peopleManagement.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressService addressService;


    @GetMapping("/all")
    public ResponseEntity<List<Address>> findAllAddress() {
        return ResponseEntity.ok(addressService.findAllAddress());
    }

    @GetMapping("/{idAddress}")
    public ResponseEntity<Address> findAddressById(@PathVariable Long idAddress) {
        return ResponseEntity.ok(addressService.findAddressById(idAddress));
    }

    @GetMapping("/{namePerson}")
    public ResponseEntity<List<Address>> findAllAddressByNamePerson(@PathVariable String namePerson) {
        return ResponseEntity.ok(addressService.findAllAddressByNamePerson(namePerson));
    }

    @PostMapping("/saveAddress")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) {
        return new ResponseEntity<>(addressService.saveAddress(address), HttpStatus.CREATED);
    }

    @PostMapping("/replaceAddress/{idAddress}")
    public ResponseEntity<Address> replacePerson(@PathVariable Long idAddress, @RequestBody Address address) throws ExceptionMessage {
        return new ResponseEntity<>(addressService.replaceAddress(idAddress, address), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{idAddress}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long idAddress) {
        addressService.deleteAddress(idAddress);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
