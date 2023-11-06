package com.example.hangar.controllers;

import com.example.hangar.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;


    @GetMapping("/statistics")
    public String allStatistics(Model model) {
        return "statistics/statistics";
    }

    @GetMapping("/statistic/car")
    public String statisticCar(Model model) {
        model.addAttribute("statisticCar", statisticsService.statisticCar());
        return "statistics/statisticCar";
    }

    @GetMapping("/statistic/driver")
    public String statisticDriver(Model model) {
        model.addAttribute("statisticDriver", statisticsService.statisticDriver());
        return "statistics/statisticDriver";
    }

    @GetMapping("/statistic/fuel")
    public String statisticFuel(Model model) {
        model.addAttribute("statisticFuel", statisticsService.statisticFuel());
        return "statistics/statisticFuel";
    }

//    @GetMapping("/statistic/transportation")
//    public String statisticTransportation(Model model) {
//        //model.addAttribute("town", townService.allTown());
//        return "statistics/statisticTransportation";
//    }
}
