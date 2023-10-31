package com.example.hangar.controllers;

import com.example.hangar.entity.Role;
import com.example.hangar.entity.User;
import com.example.hangar.repo.UserRepo;
import com.example.hangar.service.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DriverService driverService;


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUs(User user, @RequestParam String name, @RequestParam String midlname, @RequestParam String surname,
                        @RequestParam String carNumber, Map<String, Object> model) {
        User userDb = userRepo.findByUsername(user.getUsername());
        if (userDb != null || user.getPassword().length() == 0) {
            model.put("mess", "exists");
            return "registration";
        }
        int newID;
        if (userRepo.LastId() == null) {
            newID = 1;
        } else {
            newID = userRepo.LastId() + 1;
        }
        Role role = Role.DRIVER;
        try {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String pas = bCryptPasswordEncoder.encode(user.getPassword());
            userRepo.saveUser(true, pas, user.getUsername(), newID);
            userRepo.saveRole(newID, role.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        boolean bool = driverService.driverSave(name, midlname, surname, carNumber.toUpperCase(), newID);
        System.out.println(bool);
        return "redirect:/";
    }
}
