package com.example.hangar.DTO;

import com.example.hangar.entity.Car;
import com.example.hangar.entity.Transportation;

public class TransportationDTO {

    private Car car;
    private Transportation transportation;

    public TransportationDTO() {
    }

    public TransportationDTO(Car car, Transportation transportation) {
        this.car = car;
        this.transportation = transportation;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Transportation getTransportation() {
        return transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }
}
//список машин volume >volume и state=true, сорт по town, volume|