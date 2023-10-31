package com.example.hangar.service;


import com.example.hangar.DTO.TransportationDTO;
import com.example.hangar.entity.Car;
import com.example.hangar.entity.Driver;
import com.example.hangar.entity.Fuel;
import com.example.hangar.entity.Transportation;
import com.example.hangar.repo.CarRepo;
import com.example.hangar.repo.DriverRepo;
import com.example.hangar.repo.FuelRepo;
import com.example.hangar.repo.TransportationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TransportationService {
    @Autowired
    TransportationRepo transportationRepo;
    @Autowired
    CarRepo carRepo;
    @Autowired
    FuelRepo fuelRepo;
    @Autowired
    DriverRepo driverRepo;

    public String transportationSave(Integer numberDriver, String nameFuel, Integer volumeFuel,
                                     LocalDate date, String address) {
        try {
            Driver driver = driverRepo.findById(numberDriver);
            Car car = driverRepo.findById(numberDriver).getCar();
            Fuel fuel = fuelRepo.findById(fuelRepo.findByIdCarANDNameFuel(nameFuel, car.getId()));

            if (driver.isState() && car.isState() && fuel.getVolume() > volumeFuel
                    && car.getLoadCapacity() > volumeFuel && date.isAfter(LocalDate.now())) {
                carRepo.updateCarTransportation(car.getId(), car.getLoadCapacity(), car.getName(),
                        car.getNumber(), false, car.getHangar().getId());
                driverRepo.updateDriverTransportation(driver.getId(), driver.getMidlname(), driver.getName(), driver.getSurname(),
                        false, driver.getCar().getId());
                fuelRepo.updateFuelTransportation(fuel.getId(), fuel.getDelivery(), fuel.getName(),
                        fuel.getVolume() - volumeFuel, fuel.getHangar().getId());
                int newID;
                if (transportationRepo.LastId() == null) {
                    newID = 1;
                } else {
                    newID = transportationRepo.LastId() + 1;
                }
                transportationRepo.saveTransportation(newID, address, date, true, volumeFuel,
                        numberDriver, fuel.getId());

                return "save";
            } else {
                System.out.println(driver.isState());
                System.out.println(car.isState());
                System.out.println(fuel.getVolume() + ">" + volumeFuel);
                System.out.println(car.getLoadCapacity() + ">" + volumeFuel);
                System.out.println(date + " " + LocalDate.now());
                return "not save";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "not save";
        }
    }

    public Iterable<TransportationDTO> Alltransportation() {
        List<TransportationDTO> transportationDTOList = new ArrayList<>();
        List<Transportation> transportationList = transportationRepo.Alltransportation();
        Iterator<Transportation> transportationIterator = transportationList.listIterator();
        while (transportationIterator.hasNext()) {
            Transportation transportation = transportationIterator.next();
            transportationDTOList.add(new TransportationDTO(driverRepo.findById(transportation.getDriver().getId()).getCar(), transportation));
        }
        return transportationDTOList;
    }

}