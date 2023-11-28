package com.example.hangar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.example.hangar.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TownController {
    @Autowired
    private TownService townService;

    @PostMapping("/town")
    public ResponseEntity<String> townAdd(@RequestParam String name, Model model) {
        String message = townService.townSave(name);
        model.addAttribute("message", message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @PostMapping("/town/{id}/remove")
    public String townDelete(@PathVariable(value = "id") Integer id, Model model) {
        String message = townService.townDelete(id);
        model.addAttribute("message", message);
            return "redirect:/town";
    }

    @GetMapping("/town")
    public String town(Model model) {
        model.addAttribute("town", townService.allTown());
        return "town";
    }
}
