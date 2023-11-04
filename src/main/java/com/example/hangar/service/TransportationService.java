package com.example.hangar.service;


import com.example.hangar.DTO.JsonDistanceDTO;
import com.example.hangar.DTO.Points;
import com.example.hangar.DTO.TransportationDTO;
import com.example.hangar.entity.*;
import com.example.hangar.repo.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

@Service
public class TransportationService {
    @Autowired
    TransportationRepo transportationRepo;
    @Autowired
    CarRepo carRepo;
    @Autowired
    FuelRepo fuelRepo;
    @Autowired
    DriverRepo driverRepo;

    @Autowired
    HangarRepo hangarRepo;
        //          get Distance Api 2GIS

//    private int getDistance(double latSource, double lonSource, double latTarget, double lonTarget) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        Points points = new Points();
//        points.setLat(latSource);
//        points.setLon(lonSource);
//        Points points2 = new Points();
//        points2.setLat(latTarget);
//        points2.setLon(lonTarget);
//        JsonDistanceDTO jsonDistanceDTO = new JsonDistanceDTO(new Points[]{points, points2}, new int[]{0}, new int[]{1});
//        String result = objectMapper.writeValueAsString(jsonDistanceDTO);
//
//        URL url2 = new URL("https://routing.api.2gis.com/get_dist_matrix?key=2c71d9e6-d7c0-4cc1-a301-b29c09443346&version=2.0\n");
//        HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-Type", "application/json");
//        conn.setDoOutput(true);
//        try (OutputStream os = conn.getOutputStream()) {
//            byte[] input = result.getBytes("utf-8");
//            os.write(input, 0, input.length);
//        }
//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(conn.getInputStream(), "utf-8"))) {
//            StringBuilder response = new StringBuilder();
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                response.append(responseLine.trim());
//            }
//            result = String.valueOf(response);
//        }
//        result = result.substring(23, result.lastIndexOf(",\"duration\""));
//        int distance = Integer.parseInt(result);
//        //System.out.println("distance: "+distance);
//        return distance;
//    }

    private double[] getCoordinates(String address) throws IOException {
        String urL = "https://catalog.api.2gis.com/3.0/items/geocode?q=" + URLEncoder.encode(address, "utf-8") + "&fields=items.point&key=2c71d9e6-d7c0-4cc1-a301-b29c09443346";
        URL url = new URL(urL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        String res = null;
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            res = String.valueOf(content);
        } catch (final Exception ex) {
            ex.printStackTrace();
        }
        double lat = Double.parseDouble(res.substring(res.indexOf("\"lat\":") + 6, res.indexOf(",\"lon\":")));
        double lon = Double.parseDouble(res.substring(res.indexOf("\"lon\":") + 6, res.indexOf("},\"purpose")));
        // lon долгота    lat широта
        return new double[]{lat, lon};
    }

    public String transportationSave(String nameFuel, Integer volumeFuel,
                                     LocalDate date, String address) throws IOException {

        ArrayList<Hangar> hangar = (ArrayList<Hangar>) hangarRepo.hangarFuel(nameFuel,volumeFuel);
        TreeMap<Double,Integer> distHangerAddress=new TreeMap<>();
        for (Hangar h : hangar) {
            System.out.println();
            System.out.println(address);
            double[] coordinatesSource = getCoordinates(h.getTown().getName() + "," + h.getAddress());
            double[] coordinatesTarget = getCoordinates(address);
            double dist=DistanceAlgorithm.DistanceBetweenPlaces(coordinatesSource[1],coordinatesSource[0] ,
                    coordinatesTarget[1] ,coordinatesTarget[0] );
            distHangerAddress.put(dist,h.getNumber());
        }
//        {0.7105183676006466=3, 134.96783627187537=1}
//        3
        if (distHangerAddress.size()==0){
            System.out.println("В анграх недостаточно топлива.");
            return "not save";
        }
        System.out.println(distHangerAddress);
        System.out.println(distHangerAddress.get(distHangerAddress.firstKey()));





//        try {
//            Driver driver = driverRepo.findById(numberDriver);
//            Car car = driverRepo.findById(numberDriver).getCar();
//            Fuel fuel = fuelRepo.findById(fuelRepo.findByIdCarANDNameFuel(nameFuel, car.getId()));
//
//            if (driver.isState() && car.isState() && fuel.getVolume() > volumeFuel
//                    && car.getLoadCapacity() > volumeFuel && date.isAfter(LocalDate.now())) {
//                carRepo.updateCarTransportation(car.getId(), car.getLoadCapacity(), car.getName(),
//                        car.getNumber(), false, car.getHangar().getId());
//                driverRepo.updateDriverTransportation(driver.getId(), driver.getMidlname(), driver.getName(), driver.getSurname(),
//                        false, driver.getCar().getId());
//                fuelRepo.updateFuelTransportation(fuel.getId(), fuel.getDelivery(), fuel.getName(),
//                        fuel.getVolume() - volumeFuel, fuel.getHangar().getId());
//                int newID;
//                if (transportationRepo.LastId() == null) {
//                    newID = 1;
//                } else {
//                    newID = transportationRepo.LastId() + 1;
//                }
//                transportationRepo.saveTransportation(newID, address, date, true, volumeFuel,
//                        numberDriver, fuel.getId());
//
//                return "save";
//            } else {
//                System.out.println("driver state: " + driver.isState());
//                System.out.println("car state: " + car.isState());
//                System.out.println(fuel.getVolume() + ">" + volumeFuel);
//                System.out.println(car.getLoadCapacity() + ">" + volumeFuel);
//                System.out.println(date + " " + LocalDate.now());
//                return "not save";
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return "not save";
//        }
        return "nice";
    }

    public Iterable<TransportationDTO> Alltransportation() {
        List<TransportationDTO> transportationDTOList = new ArrayList<>();
        List<Transportation> transportationList = transportationRepo.Alltransportation();
        Iterator<Transportation> transportationIterator = transportationList.listIterator();
        while (transportationIterator.hasNext()) {
            Transportation transportation = transportationIterator.next();
            transportationDTOList.add(new TransportationDTO(driverRepo.findById(transportation.getDriver().getId()).getCar(), transportation));
        }
        return transportationDTOList;
    }
    class DistanceAlgorithm {
        static final double PIx = 3.141592653589793;
        static final double RADIO = 6378.16;

        public DistanceAlgorithm() {
        }

        public static double Radians(double x) {
            return x * PIx / 180;
        }

        public static double DistanceBetweenPlaces(
                double lon1,
                double lat1,
                double lon2,
                double lat2) {
            double dlon = Radians(lon2 - lon1);
            double dlat = Radians(lat2 - lat1);
            double a = (Math.sin(dlat / 2) * Math.sin(dlat / 2)) + Math.cos(Radians(lat1)) * Math.cos(Radians(lat2))
                    * (Math.sin(dlon / 2) * Math.sin(dlon / 2));
            double angle = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return (angle * RADIO) ;//distance in km
        }

    }
}