package com.jeff.importexcel;

import com.jeff.importexcel.domain.entity.Person;
import com.jeff.importexcel.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class ImportExcelApplication implements CommandLineRunner {

    private final PersonService personService;

    public static void main(String[] args) {
        SpringApplication.run(ImportExcelApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {

//            FileInputStream fichierExcel = new FileInputStream("src/main/java/com/jeff/importexcel/data/excel.xlsx");
//            List<Person> personList = personService.extractData(fichierExcel);
//            personService.save(personList);

            List<Person> personList = personService.getAll();
            Workbook workbook = personService.generateExcel(personList);
            personService.exportPersonList(workbook);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }
}
