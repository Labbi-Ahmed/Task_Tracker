package com.labbi.taskTracker.extra;

import com.labbi.taskTracker.Repository.RoleRepository;
import com.labbi.taskTracker.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.findByName("USER") == null) {
            Role roleUser = new Role();
            roleUser.setName("USER");
            roleRepository.save(roleUser);
        }

        if (roleRepository.findByName("ADMIN") == null) {
            Role roleAdmin = new Role();
            roleAdmin.setName("ADMIN");
            roleRepository.save(roleAdmin);
        }
    }
}
