package com.example.hangar.controllers;

import com.example.hangar.entity.Driver;
import com.example.hangar.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DriverController {

    @Autowired
    private DriverService driverService;

    @GetMapping("/driver")
    public String driver(Model model) {
        model.addAttribute("driver", driverService.Alldriver());
        return "driver";
    }

    @GetMapping("/driver/{id}/edit")
    public String driverEdit(@PathVariable(value = "id") Integer id, Model model) {
        Driver driver = driverService.findById(id);
        model.addAttribute("driver", driver);
        return "driver-edit";
    }
    @PostMapping("/driver/{id}/edit")
    public String driverEdit(@PathVariable(value = "id") Integer id,@RequestParam String midlname,
                             @RequestParam String name, @RequestParam String surname,
                             @RequestParam(defaultValue = "false") boolean state,
                             @RequestParam String carNumber, Model model) {
        driverService.driverUpdate(id, name , midlname, surname, carNumber, state);
        return "redirect:/driver";
    }

    @PostMapping("/driver/{id}/remove")
    public ResponseEntity<String> driverDelete(@PathVariable(value = "id") Integer id, Model model) {
        String message = driverService.driverDelete(id);
        model.addAttribute("message", message);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
