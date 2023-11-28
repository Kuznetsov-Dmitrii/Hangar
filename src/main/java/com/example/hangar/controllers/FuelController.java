package com.example.hangar.controllers;

import com.example.hangar.repo.FuelRepo;
import com.example.hangar.service.ForDriverService;
import com.example.hangar.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.logging.Logger;

@Controller
public class FuelController {
    @Autowired
    private FuelService fuelService;

    private static final Logger logger=Logger.getLogger(FuelController.class.getName());


    @PostMapping("/fuel")
    public ResponseEntity<String> fuelAdd(@RequestParam String name, @RequestParam Integer volume,
                          @RequestParam LocalDate delivery, @RequestParam Integer hangarNumber, Model model) {
        String message = fuelService.fuelSave(name, volume, delivery, hangarNumber);
        model.addAttribute("message", message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/fuel")
    public String fuel(Model model) {
        model.addAttribute("fuel", fuelService.allFuel());
        return "fuel";
    }

    @Autowired
    FuelRepo fuelRepo; // Условная доставка топлива
    @PostMapping("/deliveryFuelAllHangar")
    public String deliveryFuelAllHangar(Model model) {
        try {
            fuelRepo.deliveryFuelAllHangar();
        }catch (Exception e){
        }
        logger.info(SecurityContextHolder.getContext().getAuthentication().getName()+ " добавил топливо в ангары");
        return "redirect:/main";
    }

}
