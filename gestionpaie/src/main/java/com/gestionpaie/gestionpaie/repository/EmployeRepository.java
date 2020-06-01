package com.gestionpaie.gestionpaie.repository;

import com.gestionpaie.gestionpaie.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeRepository extends JpaRepository<Employe,Integer> {
}
