/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.datawarehouse;

import com.example.datawarehouse.dataC.DatoC;
import com.example.datawarehouse.dataC.HechoC;
import com.example.datawarehouse.dataC.HechoCService;
import com.example.datawarehouse.dataC.Hotel;
import com.example.datawarehouse.dataC.Income;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
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
public class DatawarehouseCController {

    @Autowired
    private HechoCService pagoHotelService;

    @GetMapping("/c")
    public List<DatoC> getDatos() {
        List<HechoC> hechoCs = pagoHotelService.getAll();
        List<DatoC> res = new ArrayList<>();
        res.add(createDatoC("BENI", hechoCs));
        res.add(createDatoC("CHUQUISACA", hechoCs));
        res.add(createDatoC("COCHABAMBA", hechoCs));
        res.add(createDatoC("LA PAZ", hechoCs));
        res.add(createDatoC("ORURO", hechoCs));
        res.add(createDatoC("PANDO", hechoCs));
        res.add(createDatoC("POTOSI", hechoCs));
        res.add(createDatoC("SANTA CRUZ", hechoCs));
        res.add(createDatoC("TARIJA", hechoCs));
        return res;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private DatoC createDatoC(String city, List<HechoC> hechoCs) {
        List<HechoC> hechoCsByDepartamento = hechoCs.stream().filter(hcs -> hcs.getDepartamento().equals(city)).collect(Collectors.toList());
        DatoC datoC = new DatoC();
        datoC.setCity(city);
        List<Hotel> hotels = new ArrayList<>();
        for (HechoC hechoC : hechoCsByDepartamento) {
            Hotel hotel = new Hotel();
            hotel.setName(hechoC.getNombreHotel());
            boolean anyMatch = existeHotel(hotel, hotels);
            if (anyMatch) {
                hotel = getHotelByName(hotel.getName(), hotels);
            } else {
                hotel.setIncomePerYear(new ArrayList<>());
            }
            List<Income> incomePerYear = hotel.getIncomePerYear();
            Income income = new Income();
            income.setYear(hechoC.getYear());
            income.setIncome(hechoC.getTotalMonto());
            incomePerYear.add(income);
            hotel.setIncomePerYear(incomePerYear);
            if (!hotels.contains(hotel)) {
                hotels.add(hotel);
            }
        }
        setAverage(hotels, 2017);
        setAverage(hotels, 2018);
        setAverage(hotels, 2019);
        datoC.setHotels(hotels);
        return datoC;
    }

    private boolean existeHotel(Hotel hotel, List<Hotel> hotels) {
        if (hotels.stream().anyMatch((h) -> (h.getName().equals(hotel.getName())))) {
            return true;
        }
        return false;
    }

    private Hotel getHotelByName(String name, List<Hotel> hotels) {
        return hotels.stream().filter(h -> h.getName().equals(name)).findAny().get();
    }

    private void setAverage(List<Hotel> hotels, int year) {
        int totalIncome = getTotalIncome(hotels, year);
        for (Hotel hotel : hotels) {
            for (Income income : hotel.getIncomePerYear()) {
                if (income.getYear() == year) {
                    double div = (double) income.getIncome() / totalIncome;
                    div = (double)Math.round(div * 100d) / 100d;
                    int average = (int) (div * 100);
                    income.setAverage(average);
                }
            }
        }
    }

    private int getTotalIncome(List<Hotel> hotels, int year) {
        int res = 0;
        for (Hotel hotel : hotels) {
            for (Income income : hotel.getIncomePerYear()) {
                if (income.getYear() == year) {
                    res += income.getIncome();
                }
            }
        }
        return res;
    }
}
