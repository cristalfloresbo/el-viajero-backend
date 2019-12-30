/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.datawarehouse;

import com.example.datawarehouse.dataA.DatoA;
import com.example.datawarehouse.dataA.HechoA;
import com.example.datawarehouse.dataA.HechoAService;
import com.example.datawarehouse.dataA.Hotel;
import java.util.ArrayList;
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
public class DatawarehouseAController {

    @Autowired
    private HechoAService hechoAService;

    @GetMapping("/a")
    public List<DatoA> getAll() {
        List<HechoA> hechoAs = hechoAService.getAll();
        List<DatoA> res = new ArrayList<>();
        res.add(createDatosA("BENI", hechoAs));
        res.add(createDatosA("CHUQUISACA", hechoAs));
        res.add(createDatosA("COCHABAMBA", hechoAs));
        res.add(createDatosA("LA PAZ", hechoAs));
        res.add(createDatosA("ORURO", hechoAs));
        res.add(createDatosA("PANDO", hechoAs));
        res.add(createDatosA("POTOSI", hechoAs));
        res.add(createDatosA("SANTA CRUZ", hechoAs));
        res.add(createDatosA("TARIJA", hechoAs));
        return res;
    } 
    
    private DatoA createDatosA(String city, List<HechoA> hechoAs) {
        List<HechoA> hechoAsByCity = hechoAs.stream().filter(has -> has.getDepartamento().equals(city)).collect(Collectors.toList());
        List<Hotel> hotels = new ArrayList<>();
        DatoA datoA = new DatoA();
        int media = hechoAsByCity.get(0).getMedia();
        datoA.setAverageEarnings(media);
        datoA.setCity(city);
        for (HechoA hechoA : hechoAsByCity) {
            Hotel hotel = new Hotel();
            hotel.setName(hechoA.getNombreHotel());
            hotel.setTotalIncome(hechoA.getTotalHospedaje());
            hotels.add(hotel);
        }
        datoA.setHotels(hotels);
        return datoA;
    }
}
