package com.gestionpaie.gestionpaie.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@ToString(exclude = {"revenues","journalsDePaie"})
public class Periode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    private String code;

    private Boolean active ;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    @JsonIgnore
    @OneToMany(mappedBy = "periode", cascade = CascadeType.ALL)
    private Collection<JournalDePaie> journalsDePaie;

    @JsonIgnore
    @OneToMany(mappedBy = "periode", cascade = CascadeType.ALL)
    private Collection<Revenue> revenues;
}
