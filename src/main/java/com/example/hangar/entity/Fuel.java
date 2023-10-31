package com.example.hangar.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "fuel")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length=50)
    private String name;
    @Column(length=10)
    private Integer volume;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private LocalDate delivery;

    @ManyToOne
    private Hangar hangar;

    public Fuel() {
    }

    public Fuel(String name, Integer volume, LocalDate delivery, Hangar hangar) {
        this.name = name;
        this.volume = volume;
        this.delivery = delivery;
        this.hangar = hangar;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVolume() {
        return volume;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public void setHangar(Hangar hangar) {
        this.hangar = hangar;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public LocalDate getDelivery() {
        return delivery;
    }

    public void setDelivery(LocalDate delivery) {
        this.delivery = delivery;
    }

}
