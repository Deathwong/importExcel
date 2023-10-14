package com.jeff.importexcel.service.implement;

import com.jeff.importexcel.domain.dto.PersonDto;
import com.jeff.importexcel.domain.entity.Person;
import com.jeff.importexcel.domain.mapper.PersonMapper;
import com.jeff.importexcel.repository.PersonRepository;
import com.jeff.importexcel.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@Log4j2
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    /**
     * Permet d'extraire une liste de personnes à partir d'un fichier excel
     *
     * @param fichierExcel Le fichier excel
     * @return Une liste de Personnes
     */
    public List<Person> extractData(FileInputStream fichierExcel) {

        List<PersonDto> personDtoList = new ArrayList<>();

        try {
            Workbook workbook = new XSSFWorkbook(fichierExcel);
            Sheet excelSheet = workbook.getSheetAt(0);

            for (Row row : excelSheet) {

                if (row.getRowNum() != 0) {

                    PersonDto personDto = new PersonDto();
                    for (Cell cell : row) {
                        switch (cell.getColumnIndex()) {
                            case 0:
                                personDto.setNom(cell.getStringCellValue());
                                break;
                            case 1:
                                personDto.setPrenom(cell.getStringCellValue());
                                break;
                            case 2:
                                personDto.setAge(cell.getNumericCellValue());
                                break;
                            case 3:
                                personDto.setDate(cell.getDateCellValue().toString());
                                break;
                            default:
                                break;
                        }
                    }
                    personDtoList.add(personDto);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Création list Excel Entity
        List<Person> personList = new ArrayList<>();

        // Ajout des excel dans la liste
        for (PersonDto excelDto : personDtoList) {
            Person person = personMapper.map(excelDto);
            personList.add(person);
        }


        return personList;
    }

    /**
     * Permet de générer un fichier excel à partir des personnes contenues dans la base de données
     *
     * @param personList
     * @return
     */
    public Workbook generateExcel(List<Person> personList) {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("personnes");

        Row header = sheet.createRow(0);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("nom");

        headerCell = header.createCell(1);
        headerCell.setCellValue("prenom");

        headerCell = header.createCell(2);
        headerCell.setCellValue("age");

        headerCell = header.createCell(3);
        headerCell.setCellValue("date");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.US);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        int rowIndex = 1;

        for (Person person : personList) {

            Row row = sheet.createRow(rowIndex);

            Cell cell = row.createCell(0);
            cell.setCellValue(person.getNom());

            cell = row.createCell(1);
            cell.setCellValue(person.getPrenom());

            cell = row.createCell(2);
            cell.setCellValue(person.getAge());

            cell = row.createCell(3);
            String dateNaissance = person.getDate();
            ZonedDateTime inputDateNaissance = ZonedDateTime.parse(dateNaissance, formatter);
            String formatDateNaissance = inputDateNaissance.format(outputFormatter);
            cell.setCellValue(formatDateNaissance);

            rowIndex++;
        }
        return workbook;
    }

    /**
     * Permet d'exporter une liste de personne dans un fichier excel
     *
     * @param workbook Le fichier à exporter
     */
    public void exportPersonList(Workbook workbook) {

        String path = "C:\\Users\\Jeff\\Documents\\excel\\";
        String fileName = "exportListPerson.xlsx";
        String filePath = path + fileName;
        File file = new File(filePath);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            workbook.write(fileOutputStream);
            workbook.close();

        } catch (Exception e) {
            log.error("An exception occurred when saving the export file", e);
            throw new RuntimeException(); // todo : créer une custom exception
        }
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    /**
     * Permet de sauvegarder une liste de personne.
     *
     * @param personList La liste de personnes à sauvegarder
     */
    @Override
    public void save(List<Person> personList) {
        personRepository.saveAll(personList);
    }
}
