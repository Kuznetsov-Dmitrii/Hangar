package com.example.hangar.controllers;

import com.example.hangar.service.CarService;
import com.example.hangar.service.FuelService;
import com.example.hangar.service.HangarService;
import com.example.hangar.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
    @Autowired
    private HangarService hangarService;
    @Autowired
    private FuelService fuelService;
    @Autowired
    private CarService carService;
    @Autowired
    private TownService townService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("mes", "Hi");
        return "home";
    }

    @GetMapping("/main")
    public String main(Model model) {
        return "main";
    }


}