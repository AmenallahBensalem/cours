package com.amen.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.amen.users.entities.Role;
import com.amen.users.entities.User;
import com.amen.users.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class UsersMicroserviceApplication {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(UsersMicroserviceApplication.class, args);
    }

    /*@PostConstruct
    void init_users() {
        // ajouter les rôles
        userService.addRole(new Role(null, "ADMIN"));
        userService.addRole(new Role(null, "USER"));

        // ajouter les users
        userService.saveUser(new User(null, "admin", System.getenv("SEED_ADMIN_PASSWORD"), true, null));
        userService.saveUser(new User(null, "nadhem", System.getenv("SEED_NADHEM_PASSWORD"), true, null));
        userService.saveUser(new User(null, "yassine", System.getenv("SEED_YASSINE_PASSWORD"), true, null));

        // ajouter les rôles aux users
        userService.addRoleToUser("admin", "ADMIN");
        userService.addRoleToUser("admin", "USER");

        userService.addRoleToUser("nadhem", "USER");
        userService.addRoleToUser("yassine", "USER");
    }*/
    
}