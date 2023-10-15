package com.jeff.importexcel.service;

import com.jeff.importexcel.domain.dto.PersonDto;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.util.List;

public interface ExcelService {

    /**
     * Permet d'extraire une liste de personnes à partir d'un fichier excel
     *
     * @param fichierExcel Le fichier excel
     * @return Une liste de Personnes
     */
    List<PersonDto> extractData(FileInputStream fichierExcel);

    /**
     * Permet de générer un fichier excel à partir des personnes contenues dans la base de données
     *
     * @param personDtoList La liste de personnes
     * @return L'excel
     */
    Workbook generateExcel(List<PersonDto> personDtoList);

    /**
     * Permet d'exporter une liste de personne dans un fichier excel
     *
     * @param workbook Le fichier à exporter
     */
    void exportPersonList(Workbook workbook);
}
