package br.com.attus.peopleManagement.documentation;

import br.com.attus.peopleManagement.entity.Person;
import br.com.attus.peopleManagement.exceptions.ExceptionMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PersonDocumentation {

    @Operation(
            summary = "Busca todas as Pessoas",
            description = "Retorna todos os cadastros de Person no banco de dados"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<List<Person>> findAllPerson();

    @Operation(
            summary = "Busca pessoa pelo id",
            description = "Retorna o cadastro de Person no banco de dados por meio do seu id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<Person> findPersonById(@PathVariable Long idPerson);

    @Operation(
            summary = "Busca a pessoa pelo nome de Person",
            description = "Retorna o cadastro  da pessoa no banco de dados por meio do nome"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<Person> findPersonByName(@PathVariable String namePerson);

    @Operation(
            summary = "Cadastrar Person",
            description = "Cadastra a pessoa(Person) no Banco de Dados"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<Person> savePerson(@RequestBody Person person) throws ExceptionMessage;

    @Operation(
            summary = "Atualizar pessoa",
            description = "Atualiza o cadastro de Person no Banco de Dados"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<Person> replacePerson(@PathVariable Long idPerson, @RequestBody Person person) throws ExceptionMessage;

    @Operation(
            summary = "Excluir pessoa",
            description = "Exclui a pessoa no Banco de Dados com base no id"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "requisição bem-sucedida")
            }
    )
    ResponseEntity<Void> deletePerson(@PathVariable Long idPerson);

}
