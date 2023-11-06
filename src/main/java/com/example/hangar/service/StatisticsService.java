package com.example.hangar.service;

import com.example.hangar.DTO.StatisticCarDTO;
import com.example.hangar.DTO.StatisticDriverDTO;
import com.example.hangar.DTO.StatisticFuelDTO;
import com.example.hangar.repo.CarRepo;
import com.example.hangar.repo.TransportationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsService {
    @Autowired
    private TransportationRepo transportationRepo;

    public List<StatisticCarDTO> statisticCar() {
        return transportationRepo.statisticCar();
    }

    public List<StatisticDriverDTO> statisticDriver() {
        return transportationRepo.statisticDriver();
    }

    public List<StatisticFuelDTO> statisticFuel() {
        return transportationRepo.statisticFuel();
    }


}
