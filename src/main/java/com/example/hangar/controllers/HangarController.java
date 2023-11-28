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
    public String hangar(Model model) {
        model.addAttribute("hangar", hangarService.allHangar());
        return "hangar";
    }

    @PostMapping("/hangar")
    public ResponseEntity<String> carAdd(@RequestParam String address, @RequestParam Integer number,
                         @RequestParam String town, Model model) {
        String message =hangarService.hangarSave(number, town, address);
        model.addAttribute("message", message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
