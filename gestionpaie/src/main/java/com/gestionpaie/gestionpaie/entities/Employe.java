package com.gestionpaie.gestionpaie.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@ToString(exclude = {"revenues", "prets", "conges", "absences", "journalsDePaie"})
public class Employe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    private String matricule;

    private String prenom;

    private String nom;

    private String sexe;

    private String cin;

    private String carteSejour;

    private String adresse;

    private String telephone;

    private String email;

    private String situation;

    private Integer nbrEnfants ;

    private Integer nbrDeduction;

    private String categorie;

    private Integer numCnss;

    private Integer n_PPR;

    private String identFiscale;

    private String ancienneteManuelle;

    private String status;

    private String typedePaie;

    private Float salairedeBase;

    private Integer tauxFraisPro;

    private String modeReglement;

    private String agence;

    private String numCompteBanquaire;

    private boolean cnss;

    private boolean iR;

    @Temporal(TemporalType.DATE)
    private Date datedeNaissance;

    @Temporal(TemporalType.DATE)
    private Date dateDentree;

    @Temporal(TemporalType.DATE)
    private Date dateAnciennete;

    @Temporal(TemporalType.DATE)
    private Date dateSortie;

    @ManyToOne
    private Service service;

    @ManyToOne
    private Fonction fonction;

    @ManyToOne
    private Banque banque;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private Collection<JournalDePaie> journalsDePaie;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private Collection<Revenue> revenues;

    @JsonIgnore
    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private Collection<Pret> prets;

    @JsonIgnore
    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private Collection<Conge> conges;

    @JsonIgnore
    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL)
    private Collection<Absence> absences;

}
