package com.example.hangar.service;

import com.example.hangar.controllers.FuelController;
import com.example.hangar.entity.Driver;
import com.example.hangar.repo.CarRepo;
import com.example.hangar.repo.DriverRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class DriverService {
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private CarRepo carRepo;
    private static final Logger logger=Logger.getLogger(FuelController.class.getName());

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

    public void driverUpdate(Integer id, String name, String midlname, String surname,
                             String carNumber, boolean state) {
        carNumber = carNumber.toLowerCase();
        try {
            driverRepo.updateDriver(id, midlname, name, surname, state, carRepo.findByNumber(carNumber).getId());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        logger.info(SecurityContextHolder.getContext().getAuthentication().getName()+ " изменил данные водителя с id "+id);

    }

    public String driverDelete(Integer id) {
        logger.info(SecurityContextHolder.getContext().getAuthentication().getName()+ " удалил водителя с id "+ id);
        try {
            driverRepo.deleteDriver(id);
            return "Удалено";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Iterable<Driver> Alldriver() {
        return driverRepo.Alldriver();
    }
}
