package com.gestionpaie.gestionpaie.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class RevenuePrimeId implements Serializable {
    private Integer primeId;

    private Integer revenueId;
}
