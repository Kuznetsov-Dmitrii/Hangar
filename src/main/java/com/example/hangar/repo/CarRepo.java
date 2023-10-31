package com.example.hangar.repo;

import com.example.hangar.entity.Car;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepo extends org.springframework.data.repository.Repository<Car, Long> {
    @Query(value = "SELECT * FROM Car order by state desc", nativeQuery = true)
    List<Car> Allcar();

    @Query(value = "select id from Car order by id desc limit 1", nativeQuery = true)
    Integer LastId();

    @Query(value = "select * from Car where car.number = ?1", nativeQuery = true)
    Car findByNumber(@Param("number") String number);
    @Modifying
    @Transactional
    @Query(value = "delete from Car where id = ?1", nativeQuery = true)
    void deleteCar(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "delete from Driver\n" +
            "    where car_id=?1", nativeQuery = true)
    void deleteDriver(@Param("id") Integer id);

    @Query(value = "select * from Car where car.id = ?1", nativeQuery = true)
    Car findById(@Param("id") Integer id);
    @Modifying
    @Transactional
    @Query(value = "update Car\n" +
            "set load_capacity = ?2,\n" +
            " name = ?3,\n" +
            " number = ?4,\n" +
            " state = ?5,\n" +
            " hangar_id = ?6\n" +
            "where id = ?1", nativeQuery = true)
    void updateCar(@Param("id") Integer id, @Param("loadCapacity") Integer loadCapacity,
                   @Param("name") String name, @Param("number") String number,
                   @Param("state") boolean state, @Param("hangar") Integer hangarId);
    @Query(value = "update car set state=true where  car.id=(select car.id from car\n" +
            "join driver on driver.car_id = car.id\n" +
            "where driver.id = (select driver.id from driver join login on driver.user_id=login.id where username = ?1))", nativeQuery = true)
    void carOrderComplete(@Param("username") String username);
    @Modifying
    @Transactional
    @Query(value = "update Car\n" +
            "set load_capacity = ?2,\n" +
            " name = ?3,\n" +
            " number = ?4,\n" +
            " state = ?5,\n" +
            " hangar_id = ?6\n" +
            "where id = ?1", nativeQuery = true)
    void updateCarTransportation(@Param("id") Integer id, @Param("loadCapacity") Integer loadCapacity,
                   @Param("name") String name, @Param("number") String number,
                   @Param("state") boolean state, @Param("hangar") Integer hangarId);
    @Modifying
    @Transactional
    @Query(value = "insert into Car values (?1,?2, ?3,?4,?5,?6)", nativeQuery = true)
    void saveCar(@Param("id") Integer id, @Param("loadCapacity") Integer loadCapacity, @Param("name") String name,
                 @Param("number") String number,
                 @Param("state") boolean state, @Param("hangar") Integer hangarId);
}
