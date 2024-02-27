package com.example.schooljpa.services;

import com.example.schooljpa.entities.Role;
import com.example.schooljpa.entities.User;
import com.example.schooljpa.repositories.RoleRepository;
import com.example.schooljpa.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional//charge directement  en faisant commit avec toutes les modification
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Override
    public User addNewUser(User user) {
        //generation d'un userId
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        //on charge un utilsateur
        User user = findUserByUserName(username);
        Role role = findRoleByRoleName(roleName);
        //on ajoute le role et inversement aussi
        if (user.getRoles() !=null){
            user.getRoles().add(role);
            role.getUsers().add(user);
        }
        //userRepository.save(user); // Il sera transactionnel ici
    }

    @Override
    public User authentificate(String username, String password) {
        //on cherche d'abord l'utilisateur
        User user = userRepository.findByUsername(username);
        if (user == null) throw new RuntimeException("Bad Credentials");
        if (user.getPassword().equals(password)){
            return user ;
        }
        throw new RuntimeException("Bad credentials");
    }
}
