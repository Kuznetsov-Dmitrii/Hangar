package com.example.hangar.service;

import com.example.hangar.entity.Transportation;
import com.example.hangar.repo.CarRepo;
import com.example.hangar.repo.DriverRepo;
import com.example.hangar.repo.TransportationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ForDriverService {
    private static final Logger logger=Logger.getLogger(ForDriverService.class.getName());
    @Autowired
    private TransportationRepo transportationRepo;
    @Autowired
    private DriverRepo driverRepo;
    @Autowired
    private CarRepo carRepo;
    public String orderComplete(String userName, Integer id){
        logger.info(userName+" завершил заказ № "+ id);
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
        return "Заказ завершен";
    }

    public void readyOrder(String userName){
        logger.info(SecurityContextHolder.getContext().getAuthentication().getName()+" готов к новым заказам");
        driverRepo.readyOrder(userName);
    }
    public void notReadyOrder(String userName){
        logger.info(SecurityContextHolder.getContext().getAuthentication().getName()+" не готов к новым заказам");

        driverRepo.notReadyOrder(userName);
    }
    public Iterable<Transportation> AllFlight(String username){
        return transportationRepo.AlltransportationDriver(username);
    }
}
