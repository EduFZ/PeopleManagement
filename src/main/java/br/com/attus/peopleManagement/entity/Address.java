package br.com.attus.peopleManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_address")
    private Long idAddress;
    private String street;
    private Long cep;
    private Long number;
    private String city;
    private String state;
    private Boolean mainAddress;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

}
