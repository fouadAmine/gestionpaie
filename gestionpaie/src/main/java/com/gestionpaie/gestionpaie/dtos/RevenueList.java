package com.gestionpaie.gestionpaie.dtos;

import com.gestionpaie.gestionpaie.entities.Employe;
import com.gestionpaie.gestionpaie.entities.Periode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


public class RevenueList {
    @Getter
    @Setter
    Employe employe;

    @Getter
    @Setter
    Periode periode;

    @Getter
    @Setter
    List<RevenueDto> revenues  = new ArrayList<>();
}
