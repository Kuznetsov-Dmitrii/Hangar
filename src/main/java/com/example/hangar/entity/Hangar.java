package com.example.hangar.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "hangar")
public class Hangar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 50,unique = true)
    private Integer number;

    @ManyToOne
    private Town town;
    private String address;

    public Hangar() {
    }

    public Hangar(Integer number, Town town, String address) {
        this.number = number;
        this.town = town;
        this.address = address;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }


    public void setTown(Town town) {
        this.town = town;
    }

    public Town getTown() {
        return town;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }


}
