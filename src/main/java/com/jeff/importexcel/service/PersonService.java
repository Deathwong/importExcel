package com.jeff.importexcel.service;

import com.jeff.importexcel.domain.dto.PersonDto;

import java.util.List;

public interface PersonService {

    /**
     * Permet de sauvegarder une liste de personne.
     *
     * @param personDtoList La liste de personnes à sauvegarder
     */
    void save(List<PersonDto> personDtoList);

    /**
     * Permet de récupérer la liste des personnes
     *
     * @return la liste des personnes
     */
    List<PersonDto> getAll();
}
