package com.example.hangar.controllers;

import com.example.hangar.DTO.JsonDistanceDTO;
import com.example.hangar.DTO.Points;
import com.example.hangar.DTO.Routes;
import com.example.hangar.service.TransportationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Controller
public class TransportationController implements ErrorController {

    @Autowired
    TransportationService transportationService;

    @PostMapping("/transportation")
    public String transportationAdd(@RequestParam String nameFuel, @RequestParam Integer volumeFuel,
                                    @RequestParam LocalDate date, @RequestParam String address, Model model) throws IOException {

        System.out.println(transportationService.transportationSave(nameFuel, volumeFuel,date, address));

        return "redirect:/transportation";
    }

    @GetMapping("/transportation")
    public String transportation(Model model) throws IOException {
        model.addAttribute("transportation", transportationService.Alltransportation());
        return "transportation";
    }

}
