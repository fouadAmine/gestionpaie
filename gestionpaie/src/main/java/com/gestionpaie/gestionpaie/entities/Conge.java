package com.gestionpaie.gestionpaie.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @ManyToOne
    @JoinColumn
    private TypeConge typeConge;

    @ManyToOne
    @JoinColumn
    private Employe employe;
}
