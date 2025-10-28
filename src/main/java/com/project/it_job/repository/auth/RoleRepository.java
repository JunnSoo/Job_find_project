package com.project.it_job.repository.auth;

import com.project.it_job.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {
    Role findRoleByRoleNameIgnoreCase(String roleName);
}
