package com.example.hangar.repo;


import com.example.hangar.entity.Car;
import com.example.hangar.entity.Driver;
import com.example.hangar.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.List;

@Repository
public interface DriverRepo extends org.springframework.data.repository.Repository<Driver, Long> {
    @Query(value = "select * from driver\n" +
            "where user_id in (select user_id from login\n" +
            "join user_role on login.id=user_role.user_id\n" +
            "where roles = 'DRIVER') order by state desc,id", nativeQuery = true)
    List<Driver> Alldriver();

    @Query(value = "update driver set state=true where id=(select driver.id from driver\n" +
            "join login on driver.user_id=login.id\n" +
            "where username = ?1)", nativeQuery = true)
    boolean driverOrderComplete(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "update Driver\n" +
            "set midlname = ?2,\n" +
            " name = ?3,\n" +
            " surname = ?4,\n" +
            " state = ?5,\n" +
            " car_id = ?6\n" +
            "where id = ?1", nativeQuery = true)
    void updateDriverTransportation(@Param("id") Integer id, @Param("midlname") String midlname,
                                    @Param("name") String name, @Param("surname") String surname,
                                    @Param("state") boolean state, @Param("car") Integer car_id);

    @Query(value = "select id from Driver order by id desc limit 1", nativeQuery = true)
    Integer LastId();

    @Query(value = "select id from login where login.username = ?1 ", nativeQuery = true)
    Integer findByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "update Driver\n" +
            "set midlname = ?2,\n" +
            " name = ?3,\n" +
            " surname = ?4,\n" +
            " state = ?5,\n" +
            " car_id = ?6\n" +
            "where id = ?1", nativeQuery = true)
    void updateDriver(@Param("id") Integer id, @Param("midlname") String midlname,
                      @Param("name") String name, @Param("surname") String surname,
                      @Param("state") boolean state, @Param("car") Integer car_id);

    @Modifying
    @Transactional
    @Query(value = "delete from Driver\n" +
            "    where driver.id=?1", nativeQuery = true)
    void deleteDriver(@Param("id") Integer id);

    @Query(value = "select * from Driver where driver.id = ?1", nativeQuery = true)
    Driver findById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into Driver values (?1,?2, ?3,?4,?5,?6,?7)", nativeQuery = true)
    void saveDriver(@Param("id") Integer id, @Param("midlname") String midlname, @Param("name") String name,
                    @Param("state") boolean state, @Param("surname") String surname, @Param("carid") Integer carid,
                    @Param("user_id") Integer user_id);
}