package com.jeff.importexcel.service;

import com.jeff.importexcel.domain.entity.Person;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.util.List;

public interface PersonService {

    /**
     * Permet de sauvegarder une liste de personne.
     *
     * @param personList La liste de personnes à sauvegarder
     */
    void save(List<Person> personList);

    /**
     * Permet d'extraire une liste de personnes à partir d'un fichier excel
     *
     * @param fichierExcel Le fichier excel
     * @return Une liste de Personnes
     */
    List<Person> extractData(FileInputStream fichierExcel);

    /**
     * Permet de générer un fichier excel à partir des personnes contenues dans la base de données
     */
    Workbook generateExcel(List<Person> personList);

    void exportPersonList(Workbook workbook);

    List<Person> getAll();
}
