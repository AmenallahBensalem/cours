package com.amen.users.service;

import com.amen.users.entities.Role;
import com.amen.users.entities.User;
import com.amen.users.service.register.RegistrationRequest;

import java.util.List;


public interface UserService {
    User saveUser(User user);
    User findUserByUsername(String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
    List<User> findAllUsers();
    User registerUser(RegistrationRequest request);
    User validateToken(String code);
    void sendEmailUser(User u, String code);
    
}