package com.labbi.taskTracker.Repository;

import com.labbi.taskTracker.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String user);
}
