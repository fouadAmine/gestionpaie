package com.gestionpaie.gestionpaie.repository;

import com.gestionpaie.gestionpaie.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepository extends JpaRepository<Absence,Integer> {
}
