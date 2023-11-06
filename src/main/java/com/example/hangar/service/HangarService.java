package com.example.hangar.service;



import com.example.hangar.DTO.HangarInfoDTO;
import com.example.hangar.entity.Hangar;
import com.example.hangar.repo.HangarRepo;
import com.example.hangar.repo.TownRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class HangarService {
    @Autowired
    private HangarRepo hangarRepo;
    @Autowired
    private TownRepo townRepo;
    public String hangarSave(Integer number,String town,String address){
        try {
            int newID;
            if (hangarRepo.LastId() == null){
                newID=1;
            }else {
                newID = hangarRepo.LastId() + 1;
            }
            hangarRepo.saveHangar(newID,address,number,townRepo.findByName(town).getId());
            return "save";

        }catch (Exception e){
            System.out.println(e.getMessage());
            return "notSave";
        }
    }
    public Iterable<HangarInfoDTO> allHangar(){
        Iterable<Hangar> hangars=hangarRepo.Allhangar();
        List<HangarInfoDTO> hangarInfoDTOS=new ArrayList<>();
        for (Hangar hangar:hangars){
            HangarInfoDTO hangarInfoDTO=new HangarInfoDTO(hangar.getNumber(),hangar.getAddress(),hangar.getTown().getName());
            hangarInfoDTOS.add(hangarInfoDTO);
        }
        return hangarInfoDTOS;
    }


}
