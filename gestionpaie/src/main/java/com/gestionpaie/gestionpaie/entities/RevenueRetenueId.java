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
public class RevenueRetenueId implements Serializable {
    private Integer retenueId;

    private Integer revenueId;
}
