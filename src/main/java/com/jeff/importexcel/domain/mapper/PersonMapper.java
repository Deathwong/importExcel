package com.jeff.importexcel.domain.mapper;

import com.jeff.importexcel.domain.dto.PersonDto;
import com.jeff.importexcel.domain.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person map(PersonDto personDto) {

        int age = personDto.getAge().intValue();
        return Person.builder()
                .nom(personDto.getNom())
                .prenom(personDto.getPrenom())
                .age(age)
                .date(personDto.getDate())
                .build();
    }
}
