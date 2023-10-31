package com.example.hangar.repo;


import com.example.hangar.entity.Fuel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuelRepo extends org.springframework.data.repository.Repository<Fuel, Long> {
    @Query(value = "SELECT * FROM Fuel order by hangar_id", nativeQuery = true)
    List<Fuel> Allfuel();

    @Query(value = "select id from Fuel order by id desc limit 1", nativeQuery = true)
    Integer LastId();

    @Query(value = "select * from Fuel where fuel.id = ?1", nativeQuery = true)
    Fuel findById(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "update Fuel\n" +
            "set delivery = ?2,\n" +
            " name = ?3,\n" +
            " volume = ?4,\n" +
            " hangar_id = ?5\n" +
            "where id = ?1", nativeQuery = true)
    void updateFuelTransportation(@Param("id") Integer id, @Param("delivery") LocalDate delivery, @Param("name") String name,
                                  @Param("volume") Integer volume, @Param("hangar_id") Integer hangar_id);

    @Query(value = "select fuel.id\n" +
            "from fuel\n" +
            "join hangar on hangar.id = fuel.hangar_id\n" +
            "join car on car.hangar_id = hangar.id\n" +
            "where car.id = ?2 and fuel.name = ?1", nativeQuery = true)
    Integer findByIdCarANDNameFuel(@Param("name") String name, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "insert into Fuel values (?1,?2,?3,?4,?5)", nativeQuery = true)
    void saveFuel(@Param("id") Integer newID, @Param("delivery") LocalDate delivery, @Param("name") String name,
                  @Param("volume") Integer volume, @Param("hangar_id") Integer hangar_id);
}

