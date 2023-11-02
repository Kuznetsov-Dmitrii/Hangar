package com.example.hangar.controllers;

import com.example.hangar.service.HangarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HangarController {
    @Autowired
    HangarService hangarService;

    @GetMapping("/hangar")
    public String hangar(Model model) {
        model.addAttribute("hangar", hangarService.allHangar());
        return "hangar";
    }

    @PostMapping("/hangar")
    public String carAdd(@RequestParam String address, @RequestParam Integer number,
                         @RequestParam String town, Model model) {
        hangarService.hangarSave(number, town, address);
        return "redirect:/hangar";
    }

}