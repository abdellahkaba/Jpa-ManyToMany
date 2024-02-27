package com.example.schooljpa.services;

import com.example.schooljpa.entities.Role;
import com.example.schooljpa.entities.User;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);

    //Une methode user find user byName
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    //Ajouter un role a un utilisateur
    void addRoleToUser(String username,String roleName);
    //Authentification
    User authentificate(String username,String password);
}
