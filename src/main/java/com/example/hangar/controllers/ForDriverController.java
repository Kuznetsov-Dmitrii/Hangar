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
    ForDriverService forDriverService;

    @GetMapping("/forDriver")
    public String forDriver(Principal principal, Model model) {
        System.out.println(principal.getName());
        model.addAttribute("driverFlight", forDriverService.AllFlight(principal.getName()));
        return "forDriver";
    }

    @PostMapping("/forDriver/{id}/complete")
    public String orderComplete(Principal principal,@PathVariable(value = "id") Integer id, Model model) {
        forDriverService.orderComplete(principal.getName(),id);
        return "redirect:/forDriver";
    }

}
