package com.barrilito.barrilito.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barrilito.barrilito.model.UserEntity;

public interface I_UserRepository extends JpaRepository<UserEntity, Long>{
    
    Optional<UserEntity> findByUserName(String userName);
}
