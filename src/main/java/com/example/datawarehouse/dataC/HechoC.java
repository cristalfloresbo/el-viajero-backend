/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.datawarehouse.dataC;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Cristal Flores
 */
@Data
@Entity
@Table(name = "hechos_c_dwh")
public class HechoC implements Serializable {

    @Id
    @Column(name = "id_hechos_c_dwh")
    private Long id;

    @Column(name = "DEPARTAMENTO")
    private String departamento;

    @Column(name = "NOMBRE_HOTEL")
    private String nombreHotel;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "TOTAL_MONTO")
    private Integer totalMonto;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.nombreHotel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HechoC other = (HechoC) obj;
        return Objects.equals(this.nombreHotel, other.nombreHotel);
    }

}
