package com.example.hangar.service;

import com.example.hangar.entity.Driver;
import com.example.hangar.entity.User;
import com.example.hangar.repo.CarRepo;
import com.example.hangar.repo.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverService {
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private CarRepo carRepo;

    public boolean driverSave(String name, String midlname, String surname, String carNumber,Integer userId) {
        try {
            int newID;
            if (driverRepo.LastId() == null) {
                newID = 1;
            } else {
                newID = driverRepo.LastId() + 1;
            }
            driverRepo.saveDriver(newID, midlname, name, false, surname, carRepo.findByNumber(carNumber.toLowerCase()).getId(),userId);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Driver findById(Integer id) {
        return driverRepo.findById(id);
    }

    public String driverUpdate(Integer id, String name, String midlname, String surname,
                               String carNumber, boolean state) {
        carNumber = carNumber.toLowerCase();

        try {
            driverRepo.updateDriver(id, midlname, name, surname, state, carRepo.findByNumber(carNumber).getId());
            return "save";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "not save";
        }
    }

    public String driverDelete(Integer id) {
        try {
            driverRepo.deleteDriver(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "delete";
    }

    public Iterable<Driver> Alldriver() {
        return driverRepo.Alldriver();
    }
}
