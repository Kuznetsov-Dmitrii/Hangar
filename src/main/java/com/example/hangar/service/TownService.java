package com.example.hangar.service;

import com.example.hangar.entity.Fuel;
import com.example.hangar.entity.Town;
import com.example.hangar.repo.TownRepo;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class TownService {
    @Autowired
    private TownRepo townRepo;

    public Iterable<Town> allTown() {
        return townRepo.Alltown();
    }

    public String townDelete(Integer id) {
        try {
            townRepo.deleteTown(id);
            return "Удалено";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Ошибка удаления";
        }

    }

    public String townSave(String name) {
        try {
            int newID;
            if (townRepo.LastId() == null) {
                newID = 1;
            } else {
                newID = townRepo.LastId() + 1;
            }
            townRepo.saveTown(newID + 1, name);
            return "Сохранено";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
