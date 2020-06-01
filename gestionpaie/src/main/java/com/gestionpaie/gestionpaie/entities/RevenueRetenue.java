package com.gestionpaie.gestionpaie.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "revenue_retenue")
@NoArgsConstructor
@AllArgsConstructor
public class RevenueRetenue  implements Serializable {
    @Getter
    @Setter
    @EmbeddedId
    RevenueRetenueId id ;

    @Getter
    @Setter
    Float montant;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "retenue_id")
    @MapsId("retenueId")
    Retenue retenue;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "revenue_id")
    @MapsId("revenueId")
    Revenue revenue;


}
