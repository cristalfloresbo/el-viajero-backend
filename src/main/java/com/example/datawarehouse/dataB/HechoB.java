/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.datawarehouse.dataB;

import java.io.Serializable;
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
@Table(name = "hechos_b_dwh")
public class HechoB implements Serializable {
    @Id
    @Column(name = "id_hechos_b_dwh")
    private Long id;
    
    @Column(name = "DEPARTAMENTO")
    private String departamento;
    
    @Column(name = "NOMBRE_PAQUETE_TURISTICO")
    private String nombrePaqueteTuristico;
    
    @Column(name = "PORCENTAJE")
    private Integer porcentaje;
    
}
