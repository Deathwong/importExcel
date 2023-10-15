package com.jeff.importexcel.domain.mapper;

import com.jeff.importexcel.domain.dto.PersonDto;
import com.jeff.importexcel.domain.entity.Person;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

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

        String date = personDto.getDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.US);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ZonedDateTime inputDateNaissance = ZonedDateTime.parse(date, formatter);
        String formatDateNaissance = inputDateNaissance.format(outputFormatter);

        return Person.builder()
                .nom(personDto.getNom())
                .prenom(personDto.getPrenom())
                .age(age)
                .date(LocalDate.parse(formatDateNaissance))
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
                .date(person.getDate().toString())
                .build();
    }
}
