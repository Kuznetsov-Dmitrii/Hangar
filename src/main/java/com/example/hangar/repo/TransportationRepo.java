package com.example.hangar.repo;


import com.example.hangar.DTO.StatisticCarDTO;
import com.example.hangar.DTO.StatisticDriverDTO;
import com.example.hangar.DTO.StatisticFuelDTO;
import com.example.hangar.entity.Transportation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransportationRepo extends org.springframework.data.repository.Repository<Transportation, Long> {
    @Query(value = "select fuel.name as name, sum( transportation.volume) as count\n" +
            "from transportation \n" +
            "join fuel on transportation.fuel_id = fuel.id\n" +
            "where transportation.state = 'false'\n" +
            "group by fuel.name", nativeQuery = true)
    List<StatisticFuelDTO> statisticFuel();

    @Query(value = "select id from Transportation order by id desc limit 1", nativeQuery = true)
    Integer LastId();

    @Query(value = "select car.name as name , car.number as number, count(*) as count\n " +
            "from transportation\n" +
            "join driver \n" +
            "on driver.id = transportation.driver_id\n" +
            "join car\n" +
            "on driver.car_id=car.id " +
            "group by car.number,car.name", nativeQuery = true)
    List<StatisticCarDTO> statisticCar();

    @Query(value = "select driver.id as id, driver.name as name,driver.midlname as midlname,\n" +
            "\t\tdriver.surname as surname, count(*) as count\n" +
            "from driver\n" +
            "join transportation\n" +
            "on driver.id = transportation.driver_id\n" +
            "group by driver.id\n" +
            "order by count", nativeQuery = true)
    List<StatisticDriverDTO> statisticDriver();

    @Query(value = "select count(*) from Transportation where car_id=?1", nativeQuery = true)
    Integer CountByCar(@Param("car_id") Integer car_id);

    @Query(value = "select count(*) from Transportation where driver_id=?1", nativeQuery = true)
    Integer CountByDriver(@Param("driver_id") Integer driver_id);

    //    AlltransportationDriver
    @Query(value = "SELECT * FROM Transportation order by state, departure_date", nativeQuery = true)
    List<Transportation> Alltransportation();

    @Query(value = "update Transportation set state=false where id=?1", nativeQuery = true)
    void orderComplete(@Param("id") Integer id);


    @Query(value = "select *\n" +
            "from transportation\n" +
            "where driver_id=\n" +
            "(select driver.id\n" +
            "from driver\n" +
            "join login on login.id= driver.user_id\n" +
            "where username = ?1) " +
            "order by transportation.state desc\n", nativeQuery = true)
    List<Transportation> AlltransportationDriver(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "insert into Transportation values (?1,?2, ?3,?4,?5,?6,?7)", nativeQuery = true)
    void saveTransportation(@Param("id") Integer id, @Param("address") String address, @Param("date") LocalDate date,
                            @Param("state") boolean state, @Param("volume") Integer volume,
                            @Param("driverid") Integer driverid, @Param("fuelid") Integer fuelid);


}
