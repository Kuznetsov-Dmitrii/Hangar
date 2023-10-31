package com.example.hangar.controllers;

import org.springframework.stereotype.Controller;
import com.example.hangar.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TownController {
    @Autowired
    TownService townService;

    @PostMapping("/town")
    public String townAdd(@RequestParam String name, Model model) {
        townService.townSave(name);
        return "redirect:/town";
    }

    @GetMapping("/town")
    public String town(Model model) {
        model.addAttribute("town", townService.allTown());
        return "town";
    }
}
