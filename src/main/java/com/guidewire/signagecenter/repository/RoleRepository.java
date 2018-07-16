package com.guidewire.signagecenter.repository;

import com.guidewire.signagecenter.model.Role;
import com.guidewire.signagecenter.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
