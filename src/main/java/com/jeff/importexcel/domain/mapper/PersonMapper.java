package com.jeff.importexcel.domain.mapper;

import com.jeff.importexcel.domain.dto.PersonDto;
import com.jeff.importexcel.domain.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {
    /**
     * Permet le mapping d'un personDto en Person
     *
     * @param personDto la personne Dto
     * @return Retourne un entity
     */
    public Person map(PersonDto personDto) {

        int age = personDto.getAge().intValue();
        return Person.builder()
                .nom(personDto.getNom())
                .prenom(personDto.getPrenom())
                .age(age)
                .date(personDto.getDate())
                .build();
    }

    /**
     * Permet le mapping d'un personDto en Person
     *
     * @param person la personne
     * @return Retourne un entity
     */
    public PersonDto map(Person person) {

        Double age = person.getAge().doubleValue();
        return PersonDto.builder()
                .nom(person.getNom())
                .prenom(person.getPrenom())
                .age(age)
                .date(person.getDate())
                .build();
    }
}
