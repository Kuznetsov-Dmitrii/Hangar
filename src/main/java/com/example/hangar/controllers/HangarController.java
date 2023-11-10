package com.example.hangar.controllers;

import com.example.hangar.service.HangarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HangarController {
    @Autowired
    private HangarService hangarService;

    @GetMapping("/hangar")
    public ResponseEntity<String> hangar(Model model) {
        model.addAttribute("hangar", hangarService.allHangar());
        return new ResponseEntity<>("hangar",HttpStatus.OK);
    }

    @PostMapping("/hangar")
    public String carAdd(@RequestParam String address, @RequestParam Integer number,
                         @RequestParam String town, Model model) {
        hangarService.hangarSave(number, town, address);
        return "redirect:/hangar";
    }

}
