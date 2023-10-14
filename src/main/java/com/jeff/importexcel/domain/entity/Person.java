package com.jeff.importexcel.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity(name = "Person")
@Table(name = "person")
@SequenceGenerator(name = "PersonIdGenerator", sequenceName = "PER_ID_SEQ", allocationSize = 1)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PersonIdGenerator")
    @Column(name = "per_id")
    private Long id;

    @Column(name = "per_nom")
    private String nom;

    @Column(name = "per_prenom")
    private String prenom;

    @Column(name = "per_age")
    private Integer age;

    @Column(name = "per_date")
    private String date;
}
