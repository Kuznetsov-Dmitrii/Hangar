package com.example.hangar.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "transportation")
public class Transportation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Fuel fuel;

    @Column(length = 50)
    private String address;


    @Column(length = 10)
    private Integer volume;

    @Column
    private boolean state;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private LocalDate departure_date;


    public Transportation() {}

    public Transportation(Driver driver, Fuel fuel, String address, Integer volume, boolean state, LocalDate departure_date) {
        this.driver = driver;
        this.fuel = fuel;
        this.address = address;
        this.volume = volume;
        this.state = state;
        this.departure_date = departure_date;
    }

    public LocalDate getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(LocalDate departure_date) {
        this.departure_date = departure_date;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public String getAddress() {
        return address;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }


}
