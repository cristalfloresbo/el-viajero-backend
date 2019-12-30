/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.datawarehouse;

import com.example.datawarehouse.dataB.DatoB;
import com.example.datawarehouse.dataB.HechoB;
import com.example.datawarehouse.dataB.HechoBService;
import com.example.datawarehouse.dataB.Paquete;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Cristal Flores
 */
@RestController
@RequestMapping("/datos")
public class DatawarehouseBController {

    @Autowired
    private HechoBService hechoBService;

    @GetMapping("/b")
    public List<DatoB> getDatosB() {
        List<HechoB> hechoBs = hechoBService.getAll();
        List<DatoB> res = new ArrayList();
        res.add(createHechoB("BENI", hechoBs));
        res.add(createHechoB("CHUQUISACA", hechoBs));
        res.add(createHechoB("COCHABAMBA", hechoBs));
        res.add(createHechoB("LA PAZ", hechoBs));
        res.add(createHechoB("ORURO", hechoBs));
        res.add(createHechoB("PANDO", hechoBs));
        res.add(createHechoB("POTOSI", hechoBs));
        res.add(createHechoB("SANTA CRUZ", hechoBs));
        
        return res;
    }

    private DatoB createHechoB(String city, List<HechoB> hechoBs) {
        List<HechoB> hechoBsByDepartamento = hechoBs.stream().filter(hbs -> hbs.getDepartamento().equals(city)).collect(Collectors.toList());
        DatoB datoB = new DatoB();
        datoB.setCity(city);
        List<Paquete> paquetes = new ArrayList<>();
        for (HechoB hechoB : hechoBsByDepartamento) {
            Paquete paquete = new Paquete();
            paquete.setName(hechoB.getNombrePaqueteTuristico());
            paquete.setPercentage(hechoB.getPorcentaje());
            paquetes.add(paquete);
        }
        paquetes.sort((Paquete o1, Paquete o2) -> o1.getPercentage() < o2.getPercentage() ? -1: 
                o1.getPercentage() > o2.getPercentage()? 1: 0);
        datoB.setPackages(paquetes);
        return datoB;
    }
}
