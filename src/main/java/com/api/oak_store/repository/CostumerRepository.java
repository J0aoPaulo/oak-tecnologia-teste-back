package com.api.oak_store.repository;

import com.api.oak_store.entity.Costumer;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, UUID> {
    Optional<Costumer> findByEmail(@NonNull String email);
}