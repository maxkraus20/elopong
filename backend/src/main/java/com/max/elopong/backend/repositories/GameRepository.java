package com.max.elopong.backend.repositories;


import com.max.elopong.backend.models.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameEntity, Long> {
}
