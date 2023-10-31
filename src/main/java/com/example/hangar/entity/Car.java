package com.example.hangar.entity;

import jakarta.persistence.*;

import java.util.Objects;


@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Hangar hangar;
    @Column(length=50)
    private String name;
    @Column(length=10, unique=true)
    private String number;
    private boolean state;
    @Column(length=15)
    private Integer loadCapacity;

    public Car() {
    }

    public Car(Hangar hangar, String name, String number, boolean state, Integer loadCapacity) {
        this.hangar = hangar;
        this.name = name;
        this.number = number;
        this.state = state;
        this.loadCapacity = loadCapacity;
    }

    public void setHangar(Hangar hangar) {
        this.hangar = hangar;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public void setLoadCapacity(Integer loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public boolean isState() {
        return state;
    }

    public Integer getLoadCapacity() {
        return loadCapacity;
    }


}


