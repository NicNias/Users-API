package com.app.users.repository;

import com.app.users.entity.AdmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdmRepository extends JpaRepository<AdmEntity, UUID> {
    Optional<AdmEntity> findByEmail(String email);
}
