package com.gestionpaie.gestionpaie.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name = "revenue_prime")
@NoArgsConstructor
@AllArgsConstructor
public class RevenuePrime  implements Serializable {
        @Getter
        @Setter
        @EmbeddedId
        RevenuePrimeId id ;

        @Getter
        @Setter
        Float montant;

        @Getter
        @Setter
        @ManyToOne
        @JoinColumn(name = "prime_id")
        @MapsId("primeId")
        Prime prime;

        @Getter
        @Setter
        @ManyToOne
        @MapsId("revenueId")
        @JoinColumn(name = "revenue_id")
        Revenue revenue;
}
