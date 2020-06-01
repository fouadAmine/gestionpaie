package com.gestionpaie.gestionpaie.repository;

import com.gestionpaie.gestionpaie.entities.Employe;
import com.gestionpaie.gestionpaie.entities.Periode;
import com.gestionpaie.gestionpaie.entities.Revenue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RevenueRepository extends JpaRepository<Revenue,Integer> {
    List<Revenue> findByEmployeAndPeriode(Employe employe, Periode periode);
}
