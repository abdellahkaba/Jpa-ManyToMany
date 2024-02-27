package com.example.schooljpa;

import com.example.schooljpa.entities.Role;
import com.example.schooljpa.entities.User;
import com.example.schooljpa.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SchoolJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolJpaApplication.class, args);
    }

    @Bean // s'execute au demarrage de l'application
    CommandLineRunner start (UserService userService){
        return args -> {
            User user1 = new User();
            user1.setUsername("Abdellah");
            user1.setPassword("123456");
            userService.addNewUser(user1);

            User user2 = new User();
            user2.setUsername("Mariam");
            user2.setPassword("654321");
            userService.addNewUser(user2);

            User user3 = new User();
            user3.setUsername("Oumar");
            user3.setPassword("234561");
            userService.addNewUser(user3);

            User user4 = new User();
            user4.setUsername("Fatima");
            user4.setPassword("123456");
            userService.addNewUser(user4);

            Stream.of("STUDENT","USER", "ADMIN").forEach(role -> {
                Role role1 = new Role();
                role1.setRoleName(role);
                userService.addNewRole(role1);
            });
            //NB: c'est comme sa q'uon traite une relation ManyToMany dans une app
            userService.addRoleToUser("Oumar","USER");
            userService.addRoleToUser("Abdellah","ADMIN");
            userService.addRoleToUser("Mariam","STUDENT");
            userService.addRoleToUser("Fatima","STUDENT");

            try {
                User user = userService.authentificate("Fatima","123456");
                System.out.println(user.getUserId());
                System.out.println(user.getUsername());
                System.out.println("Role => ");
                user.getRoles().forEach(role -> {
                    System.out.println("Role => " + role.toString());
                });
            }catch (Exception e){
                    e.printStackTrace();
            }
        };
    }

}
