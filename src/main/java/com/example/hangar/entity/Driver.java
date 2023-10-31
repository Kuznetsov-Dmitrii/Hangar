package com.example.hangar.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private User user;

    @ManyToOne
    private Car car;

    @Column(length = 30)
    private String name;

    @Column(length = 30)
    private String midlname;

    @Column(length = 30)
    private String surname;

    private boolean state;

    public Driver() {}

    public Driver(Car car, String name, String midlname, String surname, boolean state) {
        this.car = car;
        this.name = name;
        this.midlname = midlname;
        this.surname = surname;
        this.state = state;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMidlname(String midlname) {
        this.midlname = midlname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public String getName() {
        return name;
    }

    public String getMidlname() {
        return midlname;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isState() {
        return state;
    }
    // id_Login integer > Login.id



}
