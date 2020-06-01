package com.gestionpaie.gestionpaie.dtos;

import lombok.Data;

@Data
public class PrimeDto  {


    private Integer id ;

    private String code ;

    private String libelle;

    //@JsonProperty
    private Boolean imposableIR;

    //@JsonProperty
    private Boolean imposableCnss;

    private Float montant ;
}
