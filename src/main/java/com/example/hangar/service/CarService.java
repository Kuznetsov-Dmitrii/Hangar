package com.example.hangar.service;

import com.example.hangar.entity.Car;
import com.example.hangar.entity.Hangar;
import com.example.hangar.repo.CarRepo;
import com.example.hangar.repo.HangarRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService  {
    @Autowired
    private CarRepo carRepo;
    @Autowired
    private HangarRepo hangarRepo;
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
            return "save";
        } catch (Exception e){
            System.out.println(e.getMessage());
            return "not save";
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
        try {
            carRepo.deleteDriver(id);
            carRepo.deleteCar(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return "delete";
    }
    public Car findById(Integer id){
        return carRepo.findById(id);
    }
//    public Hangar findByNumberHangar(Integer hangarNumber){
//        return hangarRepo.findByNumberHangar(hangarNumber);
//    }
    public Iterable<Car> allCar(){
        return carRepo.Allcar();
    }
}
