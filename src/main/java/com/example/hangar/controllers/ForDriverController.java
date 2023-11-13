package com.example.hangar.controllers;

import com.example.hangar.service.ForDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class ForDriverController {
    @Autowired
    private ForDriverService forDriverService;

    @GetMapping("/forDriver")
    public String forDriver(Principal principal, Model model) {
        model.addAttribute("driverFlight", forDriverService.AllFlight(principal.getName()));
        return "forDriver";
    }

    @PostMapping("/forDriver/{id}/complete")
    public String orderComplete(Principal principal, @PathVariable(value = "id") Integer id, Model model) {
        forDriverService.orderComplete(principal.getName(), id);
        return "redirect:/";
    }

    @PostMapping("/forDriver/readyOrder")
    public String readyOrder(Principal principal,  Model model) {
        forDriverService.readyOrder(principal.getName());
        return "redirect:/";
    }

    @PostMapping("/forDriver/notReadyOrder")
    public String notReadyOrder(Principal principal,  Model model) {
        forDriverService.notReadyOrder(principal.getName());
        return "redirect:/";
    }

}
