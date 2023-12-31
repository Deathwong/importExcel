package com.jeff.importexcel.service.implement;

import com.jeff.importexcel.domain.dto.PersonDto;
import com.jeff.importexcel.exception.ExportPersonListException;
import com.jeff.importexcel.exception.ExtractDataException;
import com.jeff.importexcel.service.ExcelService;
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
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class ExcelServiceImpl implements ExcelService {

    /**
     * Permet d'extraire une liste de personnes à partir d'un fichier excel
     *
     * @param fichierExcel Le fichier excel
     * @return Une liste de Personnes
     */
    @Override
    public List<PersonDto> extractData(FileInputStream fichierExcel) {

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
            log.error("An exception occurred when extract data from the excel file", e);
            throw new ExtractDataException();
        }

        return personDtoList;
    }

    /**
     * Permet de générer un fichier excel à partir des personnes contenues dans la base de données
     *
     * @param personDtoList La liste de personnes
     * @return L'excel
     */
    public Workbook generateExcel(List<PersonDto> personDtoList) {

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

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E MMM dd HH:mm:ss zzz yyyy", Locale.US);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        int rowIndex = 1;

        for (PersonDto personDto : personDtoList) {

            Row row = sheet.createRow(rowIndex);

            Cell cell = row.createCell(0);
            cell.setCellValue(personDto.getNom());

            cell = row.createCell(1);
            cell.setCellValue(personDto.getPrenom());

            cell = row.createCell(2);
            cell.setCellValue(personDto.getAge());

            cell = row.createCell(3);
            String dateNaissance = personDto.getDate();
//            ZonedDateTime inputDateNaissance = ZonedDateTime.parse(dateNaissance, formatter);
//            String formatDateNaissance = inputDateNaissance.format(outputFormatter);
            cell.setCellValue(dateNaissance);

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
        // todo créer une méthode utilitaire pour gérer la transformation de la date
        // Create a DateTimeFormatter for the input format (ISO 8601 with nanoseconds)
        DateTimeFormatter inputFormatter = DateTimeFormatter.ISO_INSTANT;

        // Parse the date-time string into an Instant
        String instantDate = Instant.now().toString();
        Instant instant = Instant.from(inputFormatter.parse(instantDate));

        // Create a DateTimeFormatter for the desired output format
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH mm ss");

        // Format the parsed date-time as "yyyy-MM-dd HH:mm:ss"
        String formattedDate = instant.atZone(ZoneId.of("UTC")).format(outputFormatter);

        String path = "C:\\Users\\Jeff\\Documents\\excel\\";
        String fileName = "exportListPerson_" + formattedDate + ".xlsx";
        String filePath = path + fileName;
        File file = new File(filePath);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            workbook.write(fileOutputStream);
            workbook.close();

        } catch (Exception e) {
            log.error("An exception occurred when saving the export file", e);
            throw new ExportPersonListException();
        }
    }
}