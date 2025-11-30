package com.domain.profile.app.role.repository;

import com.domain.profile.app.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    @Query("select e from RoleEntity e where e.name = :name")
    Optional<RoleEntity> findByName(String name);
}
