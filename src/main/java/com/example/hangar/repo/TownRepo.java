package com.example.hangar.repo;

import com.example.hangar.entity.Town;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TownRepo extends org.springframework.data.repository.Repository<Town, Long> {

    @Query(value = "select id from Town order by id desc limit 1", nativeQuery = true)
    Integer LastId();

    @Modifying
    @Transactional
    @Query(value = "delete from Town\n" +
            "    where town.id=?1", nativeQuery = true)
    void deleteTown(@Param("id") Integer id);

    @Query(value = "SELECT * FROM Town order by name", nativeQuery = true)
    List<Town> Alltown();

    @Query(value = "select * from Town where Town.name = ?1", nativeQuery = true)
    Town findByName(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "insert into Town values (?1,?2)", nativeQuery = true)
    void saveTown(@Param("id") Integer newID, @Param("name") String name);
}
