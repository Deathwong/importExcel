package com.jeff.importexcel.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonDto {

    private String nom;

    private String prenom;

    private Double age;

    private String date;
}

