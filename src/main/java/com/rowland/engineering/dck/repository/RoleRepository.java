package com.rowland.engineering.dck.repository;

import com.rowland.engineering.dck.model.Role;
import com.rowland.engineering.dck.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
