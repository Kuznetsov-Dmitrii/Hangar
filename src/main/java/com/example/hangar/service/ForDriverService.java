package com.example.hangar.service;

import com.example.hangar.entity.Transportation;
import com.example.hangar.repo.CarRepo;
import com.example.hangar.repo.DriverRepo;
import com.example.hangar.repo.TransportationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForDriverService {
    @Autowired
    TransportationRepo transportationRepo;
    @Autowired
    DriverRepo driverRepo;
    @Autowired
    CarRepo carRepo;
    public String orderComplete(String userName,Integer id){
        try {
            driverRepo.driverOrderComplete(userName);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            transportationRepo.orderComplete(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        try {
            carRepo.carOrderComplete(userName);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "delete";
    }
    public Iterable<Transportation> AllFlight(String username){
        return transportationRepo.AlltransportationDriver(username);
    }
}
