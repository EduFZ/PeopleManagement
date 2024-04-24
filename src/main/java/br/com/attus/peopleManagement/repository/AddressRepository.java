package br.com.attus.peopleManagement.repository;

import br.com.attus.peopleManagement.entity.Address;
import br.com.attus.peopleManagement.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a")
    List<Address> findAllAddress();

    @Query("SELECT a FROM Address a WHERE a.idAddress = :idAddress")
    Address findAddressById(@Param("idAddress") Long idAddress);

    @Query("SELECT a FROM Address a WHERE a.person.name = :name")
    List<Address> findAllAddressByNamePerson(@Param("name") String name);

}
