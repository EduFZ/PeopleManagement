package br.com.attus.peopleManagement.controller;

import br.com.attus.peopleManagement.entity.Address;
import br.com.attus.peopleManagement.entity.Person;
import br.com.attus.peopleManagement.service.AddressService;
import br.com.attus.peopleManagement.util.AddressCreator;
import br.com.attus.peopleManagement.util.PersonCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AddressControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressService addressService;
    @Autowired
    private JacksonTester<Address> jsonAddress;


    @Test
    void shouldReturnStatusOkWithFindAllAddress() throws Exception {
        List<Address> address = new ArrayList<>();
        address.add(AddressCreator.createMainAddress());
        address.add(AddressCreator.createAddress());

        BDDMockito.given(addressService.findAllAddress()).willReturn(address);

        mockMvc.perform(MockMvcRequestBuilders.get("/address/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnStatusOkWithFindAddressById() throws Exception {
        Address address = AddressCreator.createMainAddress();
        Long idAddress = 1L;

        BDDMockito.given(addressService.findAddressById(BDDMockito.anyLong())).willReturn(address);

        mockMvc.perform(MockMvcRequestBuilders.get("/address/{idAddress}", idAddress)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnStatusOkWithFindAllAddressByNamePerson() throws Exception {
        List<Address> addresses = new ArrayList<>();
        addresses.add(AddressCreator.createMainAddress());
        addresses.add(AddressCreator.createAddress());
        String namePerson = "Fulano";

        BDDMockito.given(addressService.findAllAddressByNamePerson("Fulano")).willReturn(addresses);

        mockMvc.perform(MockMvcRequestBuilders.get("/address/findAddressByNamePerson/{namePerson}", namePerson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnStatusOkWithFindMainAddressByNamePerson() throws Exception {
        Address address = AddressCreator.createMainAddress();
        String namePerson = "Fulano";

        BDDMockito.given(addressService.findMainAddressByNamePerson("Fulano")).willReturn(address);

        mockMvc.perform(MockMvcRequestBuilders.get("/address/mainAddress/{namePerson}", namePerson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    void shouldReturnStatusCreatedWithSaveAddress() throws Exception {
        Address address = AddressCreator.createMainAddress();

        BDDMockito.given(addressService.saveAddress(address)).willReturn(address);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/address/saveAddress")
                        .content(jsonAddress.write(address).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());

    }

    @Test
    void shouldReturnStatusCreatedWithReplaceAddress() throws Exception {
        Address address = AddressCreator.createMainAddress();
        Long idAddress = 1L;

        BDDMockito.given(addressService.replaceAddress(idAddress, address)).willReturn(address);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.put("/address/replaceAddress/{idAddress}", idAddress)
                        .content(jsonAddress.write(address).getJson())
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        Assertions.assertEquals(201, response.getStatus());

    }

    @Test
    void shoulReturnNoContentWithDeleteAddress() throws Exception {
        Long idAddress = 1L;

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .delete("/address/delete/{idAddress}", idAddress))
                .andReturn().getResponse();

        Assertions.assertEquals(204, response.getStatus());

    }

}