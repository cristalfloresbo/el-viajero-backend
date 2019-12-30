/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.datawarehouse.dataA;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Cristal Flores
 */
@Data
public class DatoA {
    private String city;
    private List<Hotel> hotels;
    private Integer averageEarnings;
}
