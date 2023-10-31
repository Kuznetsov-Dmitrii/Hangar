package com.example.hangar.controllers;

import com.example.hangar.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class FuelController {
    @Autowired
    FuelService fuelService;

    @PostMapping("/fuel")
    public String fuelAdd(@RequestParam String name, @RequestParam Integer volume,
                          @RequestParam LocalDate delivery, @RequestParam Integer hangarNumber, Model model) {
        fuelService.fuelSave(name, volume, delivery, hangarNumber);
        return "redirect:/fuel";
    }

    @GetMapping("/fuel")
    public String fuel(Model model) {
        model.addAttribute("fuel", fuelService.allFuel());
        return "fuel";
    }
}
