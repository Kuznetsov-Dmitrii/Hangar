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


@Controller
public class TransportationController implements ErrorController {

    @Autowired
    TransportationService transportationService;

    @PostMapping("/transportation")
    public String transportationAdd(@RequestParam Integer numberDriver,
                                    @RequestParam String nameFuel, @RequestParam Integer volumeFuel,
                                    @RequestParam LocalDate date, @RequestParam String address, Model model) {
        transportationService.transportationSave(numberDriver, nameFuel, volumeFuel,date, address);
        return "redirect:/transportation";
    }

    @GetMapping("/transportation")
    public String transportation(Model model) throws IOException {
        model.addAttribute("transportation", transportationService.Alltransportation());
        String r ="Саратов,13-й Белоглинский проезд,7";

        String urL="https://catalog.api.2gis.com/3.0/items/geocode?q="+URLEncoder.encode(r, "utf-8")+"&fields=items.point&key=2c71d9e6-d7c0-4cc1-a301-b29c09443346";
         URL url = new URL(urL);
         HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            System.out.println(content);

        } catch (final Exception ex) {
            ex.printStackTrace();

        }








//        ObjectMapper objectMapper=new ObjectMapper();
//        Points points=new Points();
//        points.setLat(54.99770587584445);
//        points.setLon(82.79502868652345);
//        Points points2=new Points();
//        points2.setLat(55.04533538802211);
//        points2.setLon(82.98179626464844);
//        JsonDistanceDTO jsonDistanceDTO=new JsonDistanceDTO(new Points[]{points,points2},new int[]{0},new int[]{1});
//
//        String result=objectMapper.writeValueAsString(jsonDistanceDTO);
//        System.out.println(result);
//


//        URL url = new URL ("https://routing.api.2gis.com/get_dist_matrix?key=2c71d9e6-d7c0-4cc1-a301-b29c09443346&version=2.0\n");
//        HttpURLConnection con = (HttpURLConnection)url.openConnection();
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setDoOutput(true);
//        try(OutputStream os = con.getOutputStream()) {
//            byte[] input = result.getBytes("utf-8");
//            os.write(input, 0, input.length);
//        }
//        try(BufferedReader br = new BufferedReader(
//                new InputStreamReader(con.getInputStream(), "utf-8"))) {
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            result= String.valueOf(response);
//        }
//        System.out.println(result);
////        result = result.substring(23, result.lastIndexOf(",\"duration\""));
////        int distance= Integer.parseInt(result);
////        System.out.println(distance);









        return "transportation";
    }

}
