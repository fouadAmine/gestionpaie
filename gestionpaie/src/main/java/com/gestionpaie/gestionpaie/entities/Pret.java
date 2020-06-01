package com.gestionpaie.gestionpaie.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    private String libelle;

    private Float mensualite;

    private Float montant ;

    @Temporal(TemporalType.DATE)
    private Date premiereEcheance;

    @Temporal(TemporalType.DATE)
    private Date derniereEcheance;

    @ManyToOne
    @JoinColumn
    private Employe employe;
}
