package com.gestionpaie.gestionpaie.repository;

import com.gestionpaie.gestionpaie.entities.RevenuePrime;
import com.gestionpaie.gestionpaie.entities.RevenuePrimeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenuePrimeRepository extends JpaRepository<RevenuePrime,RevenuePrimeId> {
}
