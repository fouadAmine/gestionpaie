package com.gestionpaie.gestionpaie.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

public class RevenueDto {

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
    private Set<RetenueDto> retenues = new HashSet<>();

    @Getter
    @Setter
    private Set<PrimeDto> primes = new HashSet<>();
}
