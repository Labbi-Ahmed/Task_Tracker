package com.labbi.taskTracker.service;

import com.labbi.taskTracker.Repository.UserRepository;
import com.labbi.taskTracker.Repository.RoleRepository;
import com.labbi.taskTracker.model.Role;
import com.labbi.taskTracker.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final RoleRepository roleRepository;

    public void createNewuUser(User user){
        user.setCreateDate(Instant.now());
        Role defaultRole = roleRepository.findByName("USER");
        System.out.println(defaultRole);
        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        repository.save(user);
    }

    public User getUserByUserNameAndPassword(String email, String password ) {
        return repository.findByEmailAndPassword(email,password);
    }

    public User userExistByEmail(String email){
        User existUser = repository.findByEmail(email);
        if(existUser != null)
            return existUser;

        return null;
    }
}
