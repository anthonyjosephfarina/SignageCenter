package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.db.RoleEntity;
import com.guidewire.signagecenter.model.db.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(RoleName roleName);
}
