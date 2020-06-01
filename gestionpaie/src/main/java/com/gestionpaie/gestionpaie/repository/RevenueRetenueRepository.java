package com.gestionpaie.gestionpaie.repository;


import com.gestionpaie.gestionpaie.entities.RevenueRetenue;
import com.gestionpaie.gestionpaie.entities.RevenueRetenueId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RevenueRetenueRepository extends JpaRepository<RevenueRetenue,RevenueRetenueId> {
}
