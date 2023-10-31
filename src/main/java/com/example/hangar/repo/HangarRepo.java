package com.example.hangar.repo;

import com.example.hangar.entity.Car;
import com.example.hangar.entity.Fuel;
import com.example.hangar.entity.Hangar;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HangarRepo extends org.springframework.data.repository.Repository<Hangar, Long> {
    @Query(value = "SELECT * FROM Hangar ", nativeQuery = true)
    List<Hangar> Allhangar();

    @Query(value = "select * from Hangar where hangar.number = ?1", nativeQuery = true)
    Hangar findByNumber(@Param("number") Integer number);

    @Query(value = "select id from Hangar order by id desc limit 1", nativeQuery = true)
    Integer LastId();

    @Query(value = "select * from Hangar where hangar.number = ?1", nativeQuery = true)
    Hangar findByNumberHangar(@Param("number") Integer number);

    @Modifying
    @Transactional
    @Query(value = "insert into Hangar values (?1,?2,?3,?4)", nativeQuery = true)
    void saveHangar(@Param("id") Integer newID, @Param("address") String address, @Param("number") Integer number,
                    @Param("town_id") Integer town_id);

}
