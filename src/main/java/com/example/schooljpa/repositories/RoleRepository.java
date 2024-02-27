package com.example.schooljpa.repositories;

import com.example.schooljpa.entities.Role;
import com.example.schooljpa.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,String> {
    //Cherche un role
    Role findByRoleName(String roleName);
}
