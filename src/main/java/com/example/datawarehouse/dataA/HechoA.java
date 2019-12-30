/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.datawarehouse.dataA;

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
@Table(name = "hechos_a_dwh")
public class HechoA implements Serializable {

    @Id
    @Column(name = "id_hechos_a_dwh")
    private Long id;

    @Column(name = "DEPARTAMENTO")
    private String departamento;

    @Column(name = "NOMBRE_HOTEL")
    private String nombreHotel;

    @Column(name = "TOTAL_HOSPEDAJE")
    private Integer totalHospedaje;

    @Column(name = "MEDIA")
    private Integer media;

}
