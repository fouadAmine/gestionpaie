package com.gestionpaie.gestionpaie.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = {"employe","periode"})
public class JournalDePaie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    @ManyToOne
    @JoinColumn
    private Employe employe;

    @ManyToOne
    @JoinColumn
    private Periode periode;
}
