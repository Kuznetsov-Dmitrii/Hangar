package com.example.hangar.DTO;

public class HangarInfoDTO {
    Integer number;
    String address;
    String townName;

    public HangarInfoDTO(Integer number, String address, String townName) {
        this.number = number;
        this.address = address;
        this.townName = townName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
