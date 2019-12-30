/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.datawarehouse.dataB;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cristal Flores
 */
@Service
public class HechoBService {
    
    @Autowired
    private HechoBRespository hechoBRespository;
    
    public List<HechoB> getAll() {
        return hechoBRespository.findAll();
    }
}
