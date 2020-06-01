package com.gestionpaie.gestionpaie.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Absence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private String motif;

    @ManyToOne
    private Employe employe;

    @ManyToOne
    private TypeAbsence typeAbsence ;
}
