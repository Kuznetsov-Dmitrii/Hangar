package com.example.hangar.repo;


import com.example.hangar.entity.Transportation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransportationRepo extends org.springframework.data.repository.Repository<Transportation, Long> {

    @Query(value = "select id from Transportation order by id desc limit 1", nativeQuery = true)
    Integer LastId();

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
