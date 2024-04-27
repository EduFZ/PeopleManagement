package br.com.attus.peopleManagement.documentation;

import br.com.attus.peopleManagement.entity.Address;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface AddressDocumentation {

    @Operation(
            summary = "Busca todos os endereços",
            description = "Retorna todos os endereços cadastrados no banco de dados"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<List<Address>> findAllAddress();

    @Operation(
            summary = "Busca endereço pelo id",
            description = "Retorna o endereço cadastrado no banco de dados por meio do seu id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<Address> findAddressById(@PathVariable Long idAddress);

    @Operation(
            summary = "Busca todos os endereços pelo nome de Person",
            description = "Retorna todos os endereços cadastrados no banco de dados por meio do nome da Person relacionada ao endereço"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<List<Address>> findAllAddressByNamePerson(@PathVariable String namePerson);

    @Operation(
            summary = "Busca o endereço principal pelo nome de Person",
            description = "Retorna o endereço principal cadastrado no banco de dados por meio do nome da Person relacionada ao endereço"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<Address> findMainAddressByNamePerson(@PathVariable String namePerson);

    @Operation(
            summary = "Cadastrar endereço",
            description = "Cadastra o endereço no Banco de Dados"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<Address> saveAddress(@RequestBody Address address) throws ExceptionMessage;

    @Operation(
            summary = "Atualizar endereço",
            description = "Atualiza o endereço no Banco de Dados"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<Address> replacePerson(@PathVariable Long idAddress, @RequestBody Address address) throws ExceptionMessage;

    @Operation(
            summary = "Excluir endereço",
            description = "Exclui o endereço no Banco de Dados com base no id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<Void> deleteAddress(@PathVariable Long idAddress);

}
