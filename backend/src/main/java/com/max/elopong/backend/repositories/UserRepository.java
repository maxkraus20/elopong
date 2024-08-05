package com.max.elopong.backend.repositories;


import com.max.elopong.backend.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByInGameName(String inGameName);
    Optional<UserEntity> findByEmail(String email);
}
