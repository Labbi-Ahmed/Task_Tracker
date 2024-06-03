package com.labbi.taskTracker.Repository;

import com.labbi.taskTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
