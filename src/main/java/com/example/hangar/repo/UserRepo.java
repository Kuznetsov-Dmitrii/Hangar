package com.example.hangar.repo;

import com.example.hangar.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends org.springframework.data.repository.Repository<User, Long>{

    @Query(value = "select * from login where username = ?1", nativeQuery = true)
    User findByUsername(@Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "insert into login (active,password,username,id) values (?1,?2,?3,?4)", nativeQuery = true)
    void saveUser(@Param("active") boolean active, @Param("password") String password,
                  @Param("username") String username, @Param("id") Integer newID);

    @Query(value = "select id from login order by id desc limit 1", nativeQuery = true)
    Integer LastId();
    @Modifying
    @Transactional
    @Query(value = "insert into user_role (user_id,roles) values (?1,?2)",nativeQuery = true)
    void saveRole(@Param("user_id") Integer user_id, @Param("roles") String role);
}
