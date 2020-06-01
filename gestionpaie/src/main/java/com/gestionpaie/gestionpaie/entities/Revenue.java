package com.gestionpaie.gestionpaie.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"employe", "periode", "primes", "retenues"})
public class Revenue {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;

    @Getter
    @Setter
    private Integer nbrJoursTravaille;

    @Getter
    @Setter
    private Integer nbrJoursCongePaye;

    @Getter
    @Setter
    private Integer nbrJoursFerie;

    @Getter
    @Setter
    @OneToMany(mappedBy = "revenue", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RevenueRetenue> retenues = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(mappedBy = "revenue", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RevenuePrime> primes = new HashSet<>();

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn
    private Employe employe;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn
    private Periode periode;
}
