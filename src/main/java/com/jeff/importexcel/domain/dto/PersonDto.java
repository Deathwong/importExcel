package com.jeff.importexcel.domain.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class PersonDto {


    @NonNull
    @Size(max = 100, min = 3)
    private String nom;

    @NonNull
    @Size(max = 100, min = 3)
    private String prenom;

    @NonNull
    private Double age;

    @PastOrPresent
    private String date;
}
