package com.example.hangar.service;


import com.example.hangar.entity.Car;
import com.example.hangar.entity.Fuel;
import com.example.hangar.repo.FuelRepo;
import com.example.hangar.repo.HangarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Date;

@Service
public class FuelService {
    @Autowired
    private FuelRepo fuelRepo;
    @Autowired
    private HangarRepo hangarRepo;

    public Iterable<Fuel> allFuel() {
        return fuelRepo.Allfuel();
    }

    public String fuelSave(String name, Integer volume, LocalDate delivery, Integer hangarNumber) {
        try {
            int newID;
            if (fuelRepo.LastId() == null){
                newID=1;
            }else {
                newID = fuelRepo.LastId() + 1;
            }
            fuelRepo.saveFuel(newID, delivery, name, volume, hangarRepo.findByNumber(hangarNumber).getId());
            return "save";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "not save";
        }
    }
}
