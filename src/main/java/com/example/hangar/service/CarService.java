package com.example.hangar.service;

import com.example.hangar.controllers.FuelController;
import com.example.hangar.entity.Car;
import com.example.hangar.entity.Hangar;
import com.example.hangar.repo.CarRepo;
import com.example.hangar.repo.HangarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class CarService  {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private HangarRepo hangarRepo;
    private static final Logger logger=Logger.getLogger(FuelController.class.getName());

    public String carSave(Integer hangarNumber,String name, String number, Integer loadCapacity){
        number=number.toLowerCase();
        int newID;
        if (carRepo.LastId() == null){
            newID=1;
        }else {
            newID = carRepo.LastId() + 1;
        }
        try {
            carRepo.saveCar(newID,loadCapacity,name,number,false,hangarRepo.findByNumber(hangarNumber).getId());
            return "Сохранено";
        } catch (Exception e){
            return e.getMessage();
        }
    }
    public String carUpdate(Integer id, Integer hangarNumber,boolean state,String name, String number, Integer loadCapacity){
        number=number.toLowerCase();
                try {
            carRepo.updateCar(id,loadCapacity,name,number,state,hangarRepo.findByNumber(hangarNumber).getId());
            return "save";
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "not save";
        }
    }
    public String carDelete(Integer id) {
        logger.info(SecurityContextHolder.getContext().getAuthentication().getName()+ " удалил машину с id "+id);
        try {
            carRepo.deleteDriver(id);
            carRepo.deleteCar(id);
            return "Удалено";
        }catch (Exception e){
            return e.getMessage();
        }

    }
    public Car findById(Integer id){
        return carRepo.findById(id);
    }

    public Iterable<Car> allCar(){
        return carRepo.Allcar();
    }
}
