/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.datawarehouse.dataC;

import java.util.List;
import lombok.Data;

/**
 *
 * @author Cristal Flores
 */
@Data
public class DatoC {
    
    private String city;
    private List<Hotel> hotels;
}
