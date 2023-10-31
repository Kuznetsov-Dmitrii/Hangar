package com.example.hangar.controllers;

import com.example.hangar.entity.Car;
import com.example.hangar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CarController {
    @Autowired
    CarService carService;


    @GetMapping("/cars")
    public String car(Model model) {
        model.addAttribute("cars", carService.allCar());
        return "cars";
    }

    @PostMapping("/cars")
    public String carAdd(@RequestParam String name, @RequestParam String number,
                         @RequestParam Integer loadCapacity, @RequestParam Integer hangarNumber, Model model) {
        carService.carSave(hangarNumber, name, number, loadCapacity);
        return "redirect:/cars";
    }

    @GetMapping("/cars/{id}/edit")
    public String carEdit(@PathVariable(value = "id") Integer id, Model model) {
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        return "car-edit";
    }

    @PostMapping("/cars/{id}/edit")
    public String carEdit(@PathVariable(value = "id") Integer id, @RequestParam(defaultValue = "false") boolean state,
                          @RequestParam String name, @RequestParam String number,
                          @RequestParam Integer loadCapacity, @RequestParam Integer hangarNumber, Model model) {
        carService.carUpdate(id, hangarNumber, state, name, number, loadCapacity);
        return "redirect:/cars";
    }

    @PostMapping("/car/{id}/remove")
    public String carDelete(@PathVariable(value = "id") Integer id, Model model) {
        carService.carDelete(id);
        return "redirect:/cars";
    }
}
