package com.jeff.importexcel.service.implement;

import com.jeff.importexcel.domain.dto.PersonDto;
import com.jeff.importexcel.domain.entity.Person;
import com.jeff.importexcel.domain.mapper.PersonMapper;
import com.jeff.importexcel.repository.PersonRepository;
import com.jeff.importexcel.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    /**
     * Permet de récupérer la liste des personnes
     *
     * @return la liste des personnes
     */
    @Override
    public List<PersonDto> getAll() {
        List<Person> personList = personRepository.findAll();

        // Création list ExcelDtos
        List<PersonDto> personDtoList = new ArrayList<>();

        // Remplissage de la liste de Personnes Dto
        for (Person person : personList) {
            PersonDto personDto = personMapper.map(person);
            personDtoList.add(personDto);
        }
        return personDtoList;
    }


    /**
     * Permet de sauvegarder une liste de personne.
     *
     * @param personDtoList La liste de personnes à sauvegarder
     */
    @Override
    public void save(List<PersonDto> personDtoList) {

        // Création list Excel Entity
        List<Person> personList = new ArrayList<>();

        // Ajout des excel dans la liste
        for (PersonDto excelDto : personDtoList) {
            Person person = personMapper.map(excelDto);
            personList.add(person);
        }
        personRepository.saveAll(personList);
    }
}
