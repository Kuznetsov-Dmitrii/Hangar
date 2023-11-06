package com.example.hangar.controllers;

import com.example.hangar.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

import java.time.LocalDate;

@Controller
public class TransportationController implements ErrorController {
    @Autowired
    private TransportationService transportationService;

    @PostMapping("/transportation")
    public String transportationAdd(@RequestParam String nameFuel, @RequestParam Integer volumeFuel,
                                    @RequestParam LocalDate date, @RequestParam String address, Model model) throws IOException {
        String message=transportationService.transportationSave(nameFuel, volumeFuel,date, address);
        model.addAttribute("message",message);
        System.out.println(message);
        return "redirect:/transportation";
    }

    @GetMapping("/transportation")
    public String transportation(Model model) throws IOException {
        model.addAttribute("transportation", transportationService.Alltransportation());
        return "transportation";
    }

}
