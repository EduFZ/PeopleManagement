package br.com.attus.peopleManagement.controller;

import br.com.attus.peopleManagement.documentation.AddressDocumentation;
import br.com.attus.peopleManagement.entity.Address;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import br.com.attus.peopleManagement.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Address", description = "implementação dos métodos com retorno das requisições")
@RestController
@RequestMapping("address")
public class AddressController implements AddressDocumentation {
    @Autowired
    private AddressService addressService;


    @Override
    @GetMapping("/all")
    public ResponseEntity<List<Address>> findAllAddress() {
        return ResponseEntity.ok(addressService.findAllAddress());
    }

    @Override
    @GetMapping("/{idAddress}")
    public ResponseEntity<Address> findAddressById(@PathVariable Long idAddress) {
        return ResponseEntity.ok(addressService.findAddressById(idAddress));
    }

    @Override
    @GetMapping("/findAddressByNamePerson/{namePerson}")
    public ResponseEntity<List<Address>> findAllAddressByNamePerson(@PathVariable String namePerson) {
        return ResponseEntity.ok(addressService.findAllAddressByNamePerson(namePerson));
    }

    @Override
    @GetMapping("/mainAddress/{namePerson}")
    public ResponseEntity<Address> findMainAddressByNamePerson(@PathVariable String namePerson) {
        return ResponseEntity.ok(addressService.findMainAddressByNamePerson(namePerson));
    }

    @Override
    @PostMapping("/saveAddress")
    public ResponseEntity<Address> saveAddress(@RequestBody Address address) throws ExceptionMessage {
        return new ResponseEntity<>(addressService.saveAddress(address), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/replaceAddress/{idAddress}")
    public ResponseEntity<Address> replacePerson(@PathVariable Long idAddress, @RequestBody Address address) throws ExceptionMessage {
        return new ResponseEntity<>(addressService.replaceAddress(idAddress, address), HttpStatus.CREATED);
    }

    @Override
    @DeleteMapping("/delete/{idAddress}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long idAddress) {
        addressService.deleteAddress(idAddress);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
