package com.gestionpaie.gestionpaie.entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class TypeAbsence {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    private String code ;

    private String libelle;
}
