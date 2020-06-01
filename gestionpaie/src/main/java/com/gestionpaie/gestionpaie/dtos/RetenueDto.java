package com.gestionpaie.gestionpaie.dtos;

import lombok.Data;

@Data
public class RetenueDto {

    private Integer id ;

    private String code;

    private String libelle;

    private Boolean deduireDeSalaireNet;

    private Boolean deduireDeSalaireImposable;

    private Float montant;
}
